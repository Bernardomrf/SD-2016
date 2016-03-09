/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IRefSiteCoach;

/**
 *
 * @author brunosilva
 * @author bernardoferreira
 */
public class Coach extends Thread{
    
    private enum State { 
        WAIT_REFEREE_COMMAND, ASSEMBLE_TEAM, WATCH_TRIAL
    }
    private final IBenchCoach bench;
    private final IPlaygroundCoach playground;
    private final IRefSiteCoach refSite;
    private boolean goOn = true;
    private State internalState;
    private final String team;
    
    public Coach(IBenchCoach bench, IPlaygroundCoach playground, IRefSiteCoach refSite, String team){
        this.bench = bench;
        this.playground = playground;
        this.internalState = State.WAIT_REFEREE_COMMAND;
        this.refSite = refSite;
        this.team = team;
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
                    internalState = State.ASSEMBLE_TEAM;
                    break;
                case ASSEMBLE_TEAM:
                    bench.callContestants(team); //nao bloqueante
                    refSite.informReferee(); //transiçao
                    internalState = State.WATCH_TRIAL;
                    break;
                case WATCH_TRIAL:
                    playground.waitForTrial(); //bloqueante - espera pelo arbitro
                    bench.reviewNotes(team); //bloqueante - espera pelos jogadors
                    internalState = State.WAIT_REFEREE_COMMAND;
                    break;
            }
        }
    }




}    
