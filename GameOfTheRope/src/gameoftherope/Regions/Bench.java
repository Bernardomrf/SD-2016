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
    
    private int nBenchPlayers;
    private int nBenchPlayersA;
    private int nBenchPlayersB;
    private int wakeCoaches;
    
    public Bench(){
        nBenchPlayers = 10;
        nBenchPlayersA = 5;
        nBenchPlayersB = 5;
        wakeCoaches = 0;
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
        notifyAll();
    }

    @Override
    public synchronized void followCoachAdvice() {
        // Empty for now
    }

    @Override
    public synchronized void waitForRefCommand() {
        while(wakeCoaches == 0){
            try {
                wait();
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
    public synchronized void seatAtTheBench(String team) {
        if (team.equals("A")){
            nBenchPlayersA++;
        }
        else if (team.equals("B")){
            nBenchPlayersB++;
        }
        notifyAll();
        
        if (team.equals("A")){
            while(true){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
                if (nBenchPlayersA > 2){
                    nBenchPlayersA--;
                    break;
                }
            }
        }
        else if (team.equals("B")){
            while(true){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
                if (nBenchPlayersB > 2){
                    nBenchPlayersB--;
                    break;
                }
            }
        }
    }
}
