/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;

/**
 *
 * @author brunosilva
 */
public class Referee extends Thread{
    
    private enum State { 
        START_OF_THE_MATCH, START_OF_A_GAME, TEAMS_READY, WAIT_FOR_TRIAL_CONCLUSION, END_OF_A_GAME, END_OF_THE_MATCH
    }
    private final IRefSiteRef refSite;
    private final IPlaygroundRef playground;
    private final IBenchRef bench;
    private boolean goOn = true;
    private State internalState;
    private int trialsDone;
    private int gamesDone;
    private final int nTrials = 6;
    private final int nGames = 3;
    
    
    public Referee(IRefSiteRef refSite, IPlaygroundRef playground, IBenchRef bench){
        this.refSite = refSite;
        this.playground = playground;
        this.bench = bench;
        this.internalState = State.START_OF_THE_MATCH;
        this.trialsDone = 0;
        this.gamesDone = 0;
    }
    
    @Override
    public void run(){
        while(goOn){
            switch(internalState){
                case START_OF_THE_MATCH:
                    refSite.announceNewGame();
                    internalState= State.START_OF_A_GAME;
                    break;
                case START_OF_A_GAME:
                    playground.callTrial();
                    bench.signalCoaches();
                    internalState= State.TEAMS_READY;
                    break;
                case TEAMS_READY:
                    refSite.waitForCoach();
                    playground.startTrial();
                    internalState= State.WAIT_FOR_TRIAL_CONCLUSION;
                    break;
                case WAIT_FOR_TRIAL_CONCLUSION:
                    playground.waitForTrialConclusion();
                    playground.assertTrialDecision();
                    trialsDone ++;
                    System.err.println("trial Done");
                    if (trialsDone == nTrials){
                        internalState= State.END_OF_A_GAME;
                    }
                    else{
                        internalState= State.START_OF_A_GAME;
                    }
                    break;
                case END_OF_A_GAME:
                    trialsDone = 0;
                    refSite.declareGameWinner();
                    gamesDone ++;
                    if (gamesDone == nGames){
                        internalState= State.END_OF_THE_MATCH;
                    }
                    else{
                        internalState= State.START_OF_A_GAME;
                    }
                    break;
                case END_OF_THE_MATCH:
                    refSite.declareMatchWinner();
                    bench.setMatchFinish();
                    goOn = false;
                    break;    
                    
            }
        }
    }
}
