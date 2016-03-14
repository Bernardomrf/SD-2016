/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;

/**
 *
 * @author brunosilva
 */
public class Playground implements IPlaygroundCoach, IPlaygroundPlayer, IPlaygroundRef{

    private int rope;
    private int playersDone;
    private final int totalTrialPlayers;
    private boolean startTrial;
    private int aTrialWins;
    private int bTrialWins;
    private boolean trialFinished;
    private boolean wakeRef;
    private int playersReady;
    
    public Playground(){
        this.rope = 0;
        this.playersDone = 0;
        this.totalTrialPlayers = 6;
        this.startTrial = false;
        this.aTrialWins = 0;
        this.bTrialWins = 0;
        this.trialFinished = false;
        this.wakeRef = false;
        this.playersReady = 0;
    }

    @Override
    public synchronized void pullTheRope(int strenght, String team) {
        if(team.equals("A")){
            rope += strenght;
        }
        else if(team.equals("B")){
            rope -= strenght;
        }
    }

    @Override
    public synchronized void callTrial() {
        /*try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }*/
        rope = 0;
        playersDone = 0;
        trialFinished = false;
        startTrial = false;
    }

    @Override
    public synchronized void startTrial() {
        /*while(playersReady != 6){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }*/
        startTrial = true;
        notifyAll();
    }

    @Override
    public synchronized void assertTrialDecision() {
        if (rope > 0){
            aTrialWins++;
        }
        else if (rope < 0){
            bTrialWins++;
        }
        
        trialFinished = true;
        notifyAll();
    }

    @Override
    public synchronized void iamDone() {
        playersDone++;
        if(playersDone == totalTrialPlayers){
            wakeRef = true;
            notifyAll();
        }
    }

    @Override
    public synchronized void waitForTrial() {
        int a = 0;
        while(!trialFinished){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    @Override
    public synchronized void standInPosition() {
        //playersReady++;
        //notifyAll();
        while(!startTrial){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

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
}
