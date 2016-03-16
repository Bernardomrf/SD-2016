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
    private boolean callPlayersA;
    private boolean callPlayersB;
    private int ncallPlayersA;
    private int ncallPlayersB;
    private int [] teamAPlayers;
    private int [] teamBPlayers;
    
    public Bench(){
        nBenchPlayersA = 0;
        nBenchPlayersB = 0;
        wakeCoaches = 0;
        matchFinish = false;
        callPlayersA = false;
        callPlayersB = false;
        ncallPlayersA = 0;
        ncallPlayersB = 0;
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
    public synchronized void callContestants(String team, int elements[]) {
        if (team.equals("A")){
            callPlayersA = true;
            ncallPlayersA = 3;
            teamAPlayers = elements;
        }
        else if (team.equals("B")){
            callPlayersB = true;
            ncallPlayersB = 3;
            teamBPlayers = elements;
        }
        notifyAll();
    }

    @Override
    public synchronized void followCoachAdvice() {
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
            while(!callPlayersA){
                try {
                    wait();
                    if (matchFinish){
                        return;
                    }
                    if (ncallPlayersA != 0){
                        for (int i = 0; i < teamAPlayers.length; i++) {
                            if (teamAPlayers[i] == id) {
                                ncallPlayersA--;
                                nBenchPlayersA--;
                                return;
                            }
                        }
                    }
                    callPlayersA = false;                    
                } catch (InterruptedException ex) {}
            }
            nBenchPlayersA--;
        }
        else if (team.equals("B")){
            while(!callPlayersB){
                try {
                    wait();
                    if (matchFinish){
                        return;
                    }
                    if (ncallPlayersB != 0){
                        for (int i = 0; i < teamBPlayers.length; i++) {
                            if (teamBPlayers[i] == id) {
                                ncallPlayersB--;
                                nBenchPlayersB--;
                                return;
                            }
                        }
                    }
                    callPlayersB = false;                    
                } catch (InterruptedException ex) {}
            }
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
    
    
}
