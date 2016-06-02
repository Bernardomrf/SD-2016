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
import gameoftherope.VectorClock.VectorClock;
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
    
    private final VectorClock ownClock;
    private VectorClock returnClock;
    
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
        
        if (team.equals("A")){
            ownClock = new VectorClock(13,1);
        }else{
            ownClock = new VectorClock(13,2);
        }
       
        repo.initCoach(internalState, team, ownClock.clone());
    }
    
    @Override
    public void run(){
        
        try{
            while(goOn){
                switch(internalState){
                    case WAIT_REFEREE_COMMAND:
                        ownClock.increment();
                        returnClock = bench.waitForRefCommand(ownClock.clone()); //bloqueante - espera pelo arbitro
                        ownClock.update(returnClock);
                        
                        if(bench.hasMatchFinished()){
                            goOn = false;
                            break;
                        }
                        internalState = coachState.ASSEMBLE_TEAM;
                        repo.changeCoachState(internalState, team, ownClock.clone());
                        break;
                    case ASSEMBLE_TEAM:
                        repo.setPlayersPositions(bench.callContestants(team), team, ownClock.clone());
                        
                        ownClock.increment();
                        returnClock = bench.playersReady(team, ownClock.clone()); // bloqueia espera que os jogadores estejam todos no campo
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = refSite.informReferee(ownClock.clone()); //transiçao
                        ownClock.update(returnClock);
                        
                        internalState = coachState.WATCH_TRIAL;
                        repo.changeCoachState(internalState, team, ownClock.clone());
                        break;
                    case WATCH_TRIAL:
                        ownClock.increment();
                        returnClock = playground.waitForTrial(ownClock.clone()); //bloqueante - espera pelo arbitro
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = bench.reviewNotes(team, ownClock.clone()); //bloqueante - espera pelos jogadors
                        ownClock.update(returnClock);
                        
                        internalState = coachState.WAIT_REFEREE_COMMAND;
                        repo.changeCoachState(internalState, team, ownClock.clone());
                        break;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}    
