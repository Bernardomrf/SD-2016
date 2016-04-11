/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.ConfigRepository;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.refState;
import java.util.Map;


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
    private int gamesDone;
    private int nTrials;
    private int nGames;
    private String knockOut;
    private int rope;
    private int[] wins;
    private int[] gameWins;
    
    /**
     *
     * @param refSite
     * @param playground
     * @param bench
     * @param repo
     */
    public Referee(IRefSiteRef refSite, IPlaygroundRef playground, IBenchRef bench, GeneralRepository repo){
        config();
        this.refSite = refSite;
        this.playground = playground;
        this.bench = bench;
        this.internalState = refState.START_OF_THE_MATCH;
        this.trialsDone = 0;
        this.gamesDone = 0;
        this.knockOut = "X";
        this.repo = repo;
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
    
    private void config(){
        Map<String, Integer> settings = ConfigRepository.getRefConfigs();
        nGames = settings.get("nGames");
        nTrials = settings.get("nTrials");
 
    }
}
