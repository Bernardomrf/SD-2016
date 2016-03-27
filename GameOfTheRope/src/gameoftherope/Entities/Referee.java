/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.refState;


/**
 *
 * @author brunosilva
 */
public class Referee extends Thread{

    
    private final IRefSiteRef refSite;
    private final IPlaygroundRef playground;
    private final IBenchRef bench;
    private final GeneralRepository repo;

    private boolean goOn = true;
    private refState internalState;
    private int trialsDone;
    private int nTrialsOfGame;
    private int gamesDone;
    private final int nTrials = 6;
    private final int nGames = 3;
    private String knockOut;
    private int rope;
    private int[] wins;
    private int[] gameWins;
    
    
    public Referee(IRefSiteRef refSite, IPlaygroundRef playground, IBenchRef bench, GeneralRepository repo){
        this.refSite = refSite;
        this.playground = playground;
        this.bench = bench;
        this.internalState = refState.START_OF_THE_MATCH;
        this.trialsDone = 0;
        this.gamesDone = 0;
        this.knockOut = "X";
        this.repo = repo;
        this.nTrialsOfGame = 0;
        this.rope = 0;
        this.wins = new int[2];
        this.gameWins = new int[2];
        repo.initRef(internalState);
    }
    
    @Override
    public void run(){
        while(goOn){
            switch(internalState){
                case START_OF_THE_MATCH:
                    repo.changeRefState(internalState);
                    //System.out.println(internalState);
                    refSite.announceNewGame();
                    internalState= refState.START_OF_A_GAME;
                    repo.newGame(gamesDone);
                    repo.changeRefState(internalState);
                    break;
                case START_OF_A_GAME:
                    nTrialsOfGame = 0;
                    
                    playground.callTrial();
                    bench.signalCoaches();
                    internalState= refState.TEAMS_READY;
                    repo.newTrial(trialsDone+1);
                    repo.changeRefState(internalState);
                    break;
                case TEAMS_READY:
                    refSite.waitForCoach();
                    playground.startTrial();
                    internalState= refState.WAIT_FOR_TRIAL_CONCLUSION;
                    repo.changeRefState(internalState);
                    break;
                case WAIT_FOR_TRIAL_CONCLUSION:
                    playground.waitForTrialConclusion();
                    //System.out.println("Novo Trial");
                    playground.assertTrialDecision();
                    trialsDone ++;
                    wins = playground.getWins();
                    knockOut = playground.checkKnockout();
                    //System.err.println("trial Done");
                    if (trialsDone == nTrials || !knockOut.equals("X")){
                        //System.out.println("Knockout " + knockOut);
                        internalState= refState.END_OF_A_GAME;
                        rope = playground.getRope();
                        repo.setRope(rope);
                        repo.changeRefState(internalState);
                    }
                    else{
                        rope = playground.getRope();
                        repo.setRope(rope);
                        internalState= refState.START_OF_A_GAME;
                        repo.changeRefState(internalState);
                    }
                    break;
                case END_OF_A_GAME:
                    trialsDone = 0;
    
                    refSite.declareGameWinner(knockOut);
                    //System.out.println(gamesDone);
                    gamesDone ++;
                    if (gamesDone == nGames){
                        internalState= refState.END_OF_THE_MATCH;
                        
                        repo.changeRefState(internalState);
                    }
                    else{
                        rope = playground.getRope();
                        repo.setRope(rope);
                        internalState= refState.START_OF_A_GAME;
                        repo.setWins(wins, knockOut);
                        repo.newGame(gamesDone);
                        repo.newTrial(trialsDone);
                        repo.changeRefState(internalState);
                    }
                    break;
                case END_OF_THE_MATCH:
                    refSite.declareMatchWinner();
                    bench.setMatchFinish();
                    gameWins = playground.getGameWins();
                    repo.setWins(wins, knockOut);
                    repo.setGameWins(gameWins, gamesDone);
                    goOn = false;
                    break;    
                    
            }
        }
    }
}
