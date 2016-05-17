/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.States.coachState;

/**
 *
 * @author brunosilva
 * @author bernardoferreira
 */
public class Coach extends Thread{
    
    
    private final IBenchCoach bench;
    private final IPlaygroundCoach playground;
    private final IRefSiteCoach refSite;
    private final GeneralRepository repo;
    private boolean goOn = true;
    private coachState internalState;
    private final String team;
    
    public Coach(IBenchCoach bench, IPlaygroundCoach playground, IRefSiteCoach refSite, String team, GeneralRepository repo){
        this.bench = bench;
        this.playground = playground;
        this.internalState = coachState.WAIT_REFEREE_COMMAND;
        this.refSite = refSite;
        this.team = team;
        this.repo = repo;
        repo.initCoach(internalState, team);
    }
    
    @Override
    public void run(){
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
    }




}    
