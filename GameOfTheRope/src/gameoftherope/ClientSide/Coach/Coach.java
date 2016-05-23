/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Coach;

import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IRefSiteCoach;
import java.rmi.RemoteException;

/**
 * Class for the Coach entity.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class Coach extends Thread{
    private final IBenchCoach bench;
    private final IPlaygroundCoach playground;
    private final IRefSiteCoach refSite;
    private final IConfigRepository conf;
    private final IGeneralRepositoryCoach repo;
    private boolean goOn = true;
    private coachState internalState;
    private final String team;
    
    
    /**
     * Constructor for Coach class.
     *
     * @param bench IBenchCoach - Interface for the coach Bench.
     * @param playground IPlaygroundCoach - Interface for the coach Playground.
     * @param refSite IRefSiteCoach - Interface for the coach Ref Site.
     * @param team String - Team of the coach.
     * @param repo IGeneralRepositoryCoach - Interface for the coach General Repository.
     * @param conf IConfigRepository - Interface for the coach Config Repository.
     */
    public Coach(IBenchCoach bench, IPlaygroundCoach playground, IRefSiteCoach refSite, String team, IGeneralRepositoryCoach repo, IConfigRepository conf) throws RemoteException{
        this.bench = bench;
        this.playground = playground;
        this.conf = conf;
        this.internalState = coachState.WAIT_REFEREE_COMMAND;
        this.refSite = refSite;
        this.team = team;
        this.repo = repo;
        repo.initCoach(internalState, team);
    }
    
    @Override
    public void run(){
        
        try{
            while(goOn){
                switch(internalState){
                    case WAIT_REFEREE_COMMAND:
                        bench.waitForRefCommand(); //bloqueante - espera pelo arbitro
                        if(bench.hasMatchFinished()){
                            goOn = false;
                            break;
                        }
                        internalState = coachState.ASSEMBLE_TEAM;
                        repo.changeCoachState(internalState, team);
                        break;
                    case ASSEMBLE_TEAM:

                        repo.setPlayersPositions(bench.callContestants(team), team);
                        bench.playersReady(team); // bloqueia espera que os jogadores estejam todos no campo
                        refSite.informReferee(); //transiçao
                        internalState = coachState.WATCH_TRIAL;
                        repo.changeCoachState(internalState, team);
                        break;
                    case WATCH_TRIAL:
                        playground.waitForTrial(); //bloqueante - espera pelo arbitro
                        bench.reviewNotes(team); //bloqueante - espera pelos jogadors
                        internalState = coachState.WAIT_REFEREE_COMMAND;
                        repo.changeCoachState(internalState, team);
                        break;
                }
            }
        } catch(Exception e){
            System.exit(1);
        }
    }
}    
