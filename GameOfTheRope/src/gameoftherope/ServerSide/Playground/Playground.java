/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.Playground;

import gameoftherope.ServerSide.ConfigRepository.ConfigRepository;
import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;

/**
 * Class to implement the playground monitor.
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrf@ua.pt]
 */
public class Playground implements IPlaygroundCoach, IPlaygroundPlayer, IPlaygroundRef{

    private int knockOutForce;
    private int nTrialsOfGameDefault;
    private int totalTrialPlayers;
    private int nCoaches;
    private int pullTheRopeSleep;
    
    private int rope;
    private int playersDone;
    private boolean startTrial;
    private int aTrialWins;
    private int bTrialWins;
    private boolean trialFinished;
    private boolean wakeRef;
    private int playersReady;
    private int coachesWaiting;
    private boolean knockOutA;
    private boolean knockOutB;
    private int nTrials;
    private int nTrialsOfGame;
    private final int allWins[];
    private final int allGameWins[];
    
    /**
     * Constructor for Playground class
     * @param configHostName - Host name for configs
     * @param portNum - Port number for configs
     */
    public Playground(){
        config();
        this.rope = 0;
        this.playersDone = 0;
        this.startTrial = false;
        this.aTrialWins = 0;
        this.bTrialWins = 0;
        this.trialFinished = false;
        this.wakeRef = false;
        this.playersReady = 0;
        this.coachesWaiting = 2;
        this.knockOutA = false;
        this.knockOutB = false;
        this.nTrials=0;
        this.nTrialsOfGame=0;
        this.allWins = new int[2];
        this.allGameWins = new int[2];
        this.allGameWins[0] = 0;
        this.allGameWins[1] = 0;
    }

    /**
     * Method used to pull the rope, changes are made to the rope before sleep is performed.
     * Method can only be called by players.
     *
     * @param strenght int - Strength of the player that is pulling the rope.
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    @Override
    public synchronized void pullTheRope(int strenght, String team) {
        if(team.equals("A")){
            rope += strenght;
        }
        else if(team.equals("B")){
            rope -= strenght;
        }
        try {
              Thread.sleep(pullTheRopeSleep);
        } catch (InterruptedException ex) {}
        
    }

    /**
     * Method used to reset current trial and call a new one.
     * It is called by the referee only.
     * Blocks until coaches are ready for the start of the new trial.
     *
     */
    @Override
    public synchronized void callTrial() {
        while(coachesWaiting != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        playersDone = 0;
        trialFinished = false;
        startTrial = false;
        coachesWaiting = 0;
        playersReady = 0;
        knockOutA = false;
        knockOutB = false;   
    }

    /**
     * Method used to start a new trial.
     * It's called by the referee only.
     * Notifies all entities that trail has begun.
     *
     */
    @Override
    public synchronized void startTrial() {
        startTrial = true;
        nTrials++;
        notifyAll();
    }

    /**
     * Method used to evaluate who won the trial.
     * It's able to distinguish between normal win and knockout win.
     * Method is called by the referee only.
     * Notifies players and coaches.
     * 
     */
    @Override
    public synchronized void assertTrialDecision() {
        if (rope > 0){
            if(rope >= knockOutForce){
                knockOutA = true;
            }
            aTrialWins++;
        }
        else if (rope < 0){
            if(rope <= (-knockOutForce)){
                knockOutB = true;
            }
            bTrialWins++;
        }
        nTrialsOfGame++;
        trialFinished = true;
        notifyAll();
    }

    /**
     * Method used to represent the state after players play.
     * It's called by players only.
     */
    @Override
    public synchronized void iamDone() {
        playersDone++;
        if(playersDone == totalTrialPlayers){
            wakeRef = true;
            notifyAll();
        }
    }

    /**
     * Method used to wait for the trial to end.
     * It's called by the coaches only and blocks until the game ends.
     */
    @Override
    public synchronized void waitForTrial() {
        while(!trialFinished){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        coachesWaiting++;
        notifyAll();
    }

    /**
     * Method used to stand in position on the playground waiting.
     * It's called by the players only and blocks until everyone is on the playground.
     * @return nTrials - number of trials
     */
    @Override
    public synchronized int standInPosition() {
        playersReady++;
        notifyAll();
        while(!startTrial || playersReady!=totalTrialPlayers){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        return nTrials;
    }

    /**
     * Method that blocks until trial is ongoing.
     * It's called by the referee only.
     */
    @Override
    public synchronized void waitForTrialConclusion() {
        while (!wakeRef){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        wakeRef = false;
    }

    /**
     * Method used to check if a knockout occured during the trial.
     * It's called by the referee only..
     * 
     * @return String - Representation of knockout or not and for what team
     */
    @Override
    public synchronized String checkKnockout() {
        if (knockOutA){
            nTrialsOfGame=0;
            aTrialWins = 0;
            bTrialWins = 0;
            rope = 0;
            allGameWins[0]++;
            return "A";
        }
        else if (knockOutB){
            nTrialsOfGame=0;
            aTrialWins = 0;
            bTrialWins = 0;
            rope=0;
            allGameWins[1]++;
            return "B";
        }
        if(nTrialsOfGame == nTrialsOfGameDefault){
            if(aTrialWins>bTrialWins){
                allGameWins[0]++;
            }
            else if(aTrialWins<bTrialWins){
                allGameWins[1]++;
            }
            rope=0;
            nTrialsOfGame=0;
            aTrialWins = 0;
            bTrialWins = 0;
        }
        return "X";
    }
    
    /**
     * Method used to return the current position of the rope
     * @return int - Position of the rope
     */
    @Override
    public synchronized int getRope(){
        return rope;
    }

    /**
     * Method used to get the number of trial wins if each team.
     * @return int[] - Number of trial wins if each team.
     */
    @Override
    public synchronized int[] getWins() {
        allWins[0] = aTrialWins;
        allWins[1] = bTrialWins;
        return allWins;
    }

    /**
     * Method used to get the number of game wins if each team.
     * @return int[] - Number of wins if each team.
     */
    @Override
    public synchronized int[] getGameWins() {
        return allGameWins;
    }
    
    private void config(){
        ConfigRepository conf = new ConfigRepository();
        PlaygroundConfig settings = conf.getPlaygroundConfig();
        
        knockOutForce = settings.getKnockOutForce();
        nTrialsOfGameDefault = settings.getnTrials();
        totalTrialPlayers = settings.getTotalTrialPlayers();
        nCoaches = settings.getNcoaches();
        pullTheRopeSleep = settings.getPullTheRopeSleep();
    }

    /**
     * Method unused in this implementation of the interface.
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
