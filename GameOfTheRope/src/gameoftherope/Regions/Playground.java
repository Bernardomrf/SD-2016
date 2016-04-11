/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.ConfigRepository;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import java.util.Map;

/**
 *
 * @author brunosilva
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
    private int allWins[];
    private int allGameWins[];
    
    /**
     *
     */
    public Playground(){
        this.config();
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
     *
     * @param strenght
     * @param team
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
     *
     */
    @Override
    public synchronized void startTrial() {
        startTrial = true;
        nTrials++;
        notifyAll();
    }

    /**
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
     *
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
     *
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
     *
     * @return
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
     *
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
     *
     * @return
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
     *
     * @return
     */
    @Override
    public synchronized int getRope(){
        return rope;
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized int[] getWins() {
        allWins[0] = aTrialWins;
        allWins[1] = bTrialWins;
        return allWins;
    }

    /**
     *
     * @return
     */
    @Override
    public synchronized int[] getGameWins() {
        return allGameWins;
    }
    
    private void config(){
        Map<String, Integer> settings = ConfigRepository.getPlaygroundConfigs();
        knockOutForce = settings.get("knockOutForce");
        nTrialsOfGameDefault = settings.get("nTrialsOfGameDefault");
        totalTrialPlayers = settings.get("totalTrialPlayers");
        nCoaches = settings.get("nCoaches");  
        pullTheRopeSleep = settings.get("pullTheRopeSleep"); 
    }
    
}
