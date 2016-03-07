/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

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
    private boolean goOn = true;
    private State internalState;
    private int trialsDone;
    private int gamesDone;
    private final int nTrials = 6;
    private final int nGames = 3;
    
    
    public Referee(IRefSiteRef refSite, IPlaygroundRef playground){
        this.refSite = refSite;
        this.playground = playground;
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
                    internalState= State.TEAMS_READY;
                    break;
                case TEAMS_READY:
                    playground.waitForCoach();
                    playground.startTrial();
                    internalState= State.WAIT_FOR_TRIAL_CONCLUSION;
                    break;
                case WAIT_FOR_TRIAL_CONCLUSION:
                    playground.waitForTrialConclusion();
                    playground.assertTrialDecision();
                    trialsDone ++;
                    if (trialsDone == nTrials){
                        internalState= State.END_OF_A_GAME;
                    }
                    internalState= State.TEAMS_READY;
                    break;
                case END_OF_A_GAME:
                    refSite.declareGameWinner();
                    gamesDone ++;
                    if (gamesDone == nGames){
                        internalState= State.END_OF_THE_MATCH;
                    }
                    internalState= State.START_OF_A_GAME;
                    break;
                case END_OF_THE_MATCH:
                    refSite.declareMatchWinner();
                    playground.setGameFinish();
                    goOn = false;
                    break;    
                    
            }
        }
    }
}
