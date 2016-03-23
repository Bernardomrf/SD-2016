/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int coachesWaiting;

    
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
        coachesWaiting = 0;
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
    public synchronized int [] callContestants(String team) {
        if (team.equals("A")){
            callPlayersA = 3;
            playersToPlayA = generateRandom(5);
            notifyAll();
            return playersToPlayA;

        }
        else if (team.equals("B")){
            callPlayersB = 3;
            playersToPlayB = generateRandom(5);
            notifyAll();
            return playersToPlayB;
        }
        return null;
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
        coachesWaiting++;
        notifyAll();
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
        coachesWaiting--;
    }
    
    @Override
    public synchronized void signalCoaches(){
        wakeCoaches = 2;
        notifyAll();
    }
    
    

    @Override
    public synchronized boolean seatAtTheBench(String team, int id) {
        if (team.equals("A")){
            while(callPlayersA == 0){
                try {
                    wait();
                    if (matchFinish){
                        return false;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayA, id)){
                    if (callPlayersA != 0){
                        nBenchPlayersA--;
                        return false;
                    }
                }
            }
            callPlayersA--;
            nBenchPlayersA--;
            return true;
        }
        else if (team.equals("B")){
            while(callPlayersB == 0){
                try {
                    wait();
                    if (matchFinish){
                        return false;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayB, id)){
                    if (callPlayersB != 0){
                        nBenchPlayersB--;
                        return false;
                    }
                }
            }
            callPlayersB--;
            nBenchPlayersB--;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean hasMatchFinished() {
        return matchFinish;
    }

    @Override
    public synchronized void setMatchFinish() {
        matchFinish = true;
        while(coachesWaiting != 2){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
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
                    if (matchFinish){
                        return;
                    }
                } catch (InterruptedException ex) {}
            }
            playersReadyA = 0;
        }
        else if (team.equals("B")){
            while (playersReadyB != 3) {            
                try {
                    wait();
                    if (matchFinish){
                        return;
                    }
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
    
    private int [] generateRandom(int maxValue){
        int i = 0;
        int tmp;
        int [] array = new int[3];
        while(i != 3){
            tmp = new Random().nextInt(maxValue);
            if (!contains(array, tmp)){
                array[i] = tmp;
                i++;
            }
        }
        return array;
    }
}
