/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;

/**
 *
 * @author brunosilva
 */
public class Bench implements IBenchCoach, IBenchPlayer, IBenchRef{
    
    private int nBenchPlayersA;
    private int nBenchPlayersB;
    private int wakeCoaches;
    private boolean matchFinish;
    private int callPlayersA;
    private int callPlayersB;
    private int [] playersToPlayA;
    private int [] playersToPlayB;
    private int playersReadyA;
    private int playersReadyB;

    
    public Bench(){
        nBenchPlayersA = 0;
        nBenchPlayersB = 0;
        wakeCoaches = 0;
        matchFinish = false;
        callPlayersA = 0;
        callPlayersB = 0;
        playersToPlayA = new int[3];
        playersToPlayB = new int[3];
        playersReadyA = 0;
        playersReadyB = 0;
    }
    

    @Override
    public synchronized void reviewNotes(String team) {
        if (team.equals("A")){
            while (nBenchPlayersA != 5){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
        }
        else if (team.equals("B")){
            while (nBenchPlayersB != 5){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    @Override
    public synchronized void callContestants(String team) {
        if (team.equals("A")){
            callPlayersA = 3;
            playersToPlayA[0] = 4;
            playersToPlayA[1] = 3;
            playersToPlayA[2] = 2;

        }
        else if (team.equals("B")){
            callPlayersB = 3;
            playersToPlayB[0] = 4;
            playersToPlayB[1] = 3;
            playersToPlayB[2] = 2;
        }
        notifyAll();
    }

    @Override
    public synchronized void followCoachAdvice(String team) {
        if (team.equals("A")){
            playersReadyA++;
        }
        else if (team.equals("B")){
            playersReadyB++;
        }
        notifyAll();
    }

    @Override
    public synchronized void waitForRefCommand() {
        while(wakeCoaches == 0){
            try {
                wait();
                if (matchFinish){
                    return;
                }
            } catch (InterruptedException ex) {
            }
        }
        wakeCoaches--;
    }
    
    @Override
    public synchronized void signalCoaches(){
        wakeCoaches = 2;
        notifyAll();
    }
    
    

    @Override
    public synchronized void seatAtTheBench(String team, int id) {
        if (team.equals("A")){
            while(callPlayersA == 0){
                try {
                    wait();
                    if (matchFinish){
                        return;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayA, id)){
                    try {
                        wait();
                        if (matchFinish){
                            return;
                        }
                    } catch (InterruptedException ex) {}
                }
            }
            callPlayersA--;
            nBenchPlayersA--;
        }
        else if (team.equals("B")){
            while(callPlayersB == 0){
                try {
                    wait();
                    if (matchFinish){
                        return;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayB, id)){
                    try {
                        wait();
                        if (matchFinish){
                            return;
                        }
                    } catch (InterruptedException ex) {}
                }
            }
            callPlayersB--;
            nBenchPlayersB--;
        }
    }

    @Override
    public synchronized boolean hasMatchFinished() {
        return matchFinish;
    }

    @Override
    public synchronized void setMatchFinish() {
        matchFinish = true;
        notifyAll();
    }

    @Override
    public synchronized void seatDown(String team) {
        if (team.equals("A")){
            nBenchPlayersA++;
        }
        else if (team.equals("B")){
            nBenchPlayersB++;
        }
        notifyAll();
    }

    @Override
    public synchronized void playersReady(String team) {
        if (team.equals("A")){
            while (playersReadyA != 3) {            
                try {
                    wait();
                } catch (InterruptedException ex) {}
            }
            playersReadyA = 0;
        }
        else if (team.equals("B")){
            while (playersReadyB != 3) {            
                try {
                    wait();
                } catch (InterruptedException ex) {}
            }
            playersReadyB = 0;
        }
    }
    
    private boolean contains(int [] array, int value){
        for(int i = 0; i < array.length; i++){
            if (array[i] == value)
                return true;
        }
        return false;
    }
}
