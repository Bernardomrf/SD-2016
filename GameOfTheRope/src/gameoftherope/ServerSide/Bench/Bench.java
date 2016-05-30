/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.Bench;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Interfaces.IBench;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.ServerSide.ConfigRepository.ConfigRepository;
import java.util.Random;

/**
 * Class to implement the bench monitor.
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class Bench implements IBenchCoach, IBenchPlayer, IBenchRef, IBench{
    
    private int nCoaches;
    private int nTeamPlayers;
    private int nTrialPlayers;
    
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

    /**
     * Constructor for Bench class
     * @param configHostName - Host name for configs
     * @param portNum - Port number for configs
     */
    public Bench(){
        config();
        nBenchPlayersA = 0;
        nBenchPlayersB = 0;
        wakeCoaches = 0;
        matchFinish = false;
        callPlayersA = 0;
        callPlayersB = 0;
        playersToPlayA = new int[nTrialPlayers];
        playersToPlayB = new int[nTrialPlayers];
        playersReadyA = 0;
        playersReadyB = 0;
        coachesWaiting = 0;
    }
    
    /**  
     * Method blocks and waits for all players to be seated at the bench.
     * It's called by coaches only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    @Override
    public synchronized void reviewNotes(String team) {
        if (team.equals("A")){
            while (nBenchPlayersA != nTeamPlayers){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
        }
        else if (team.equals("B")){
            while (nBenchPlayersB != nTeamPlayers){
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    /**
     * This method generates an array of random player numbers to be called to play.
     * Method does not block, and notifies players.
     * Method called by coaches only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @return int[] - An array containing the players that will play.
     */
    @Override
    public synchronized int [] callContestants(String team) {
        if (team.equals("A")){
            callPlayersA = nTrialPlayers;
            playersToPlayA = generateRandom(nTeamPlayers);
            notifyAll();
            return playersToPlayA;

        }
        else if (team.equals("B")){
            callPlayersB = nTrialPlayers;
            playersToPlayB = generateRandom(nTeamPlayers);
            notifyAll();
            return playersToPlayB;
        }
        return null;
    }

    /**
     * This method makes players ready to play.
     * Method does not block, and notifies coaches.
     * Method called by players only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
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

    /**
     * Method blocks and waits for the referee to come to the bench.
     * It's called by coaches only. 
     */
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
    
    /** 
     * Method does not block and notifies the coaches.
     * It's called by the referee only.
     */
    @Override
    public synchronized void signalCoaches(){
        wakeCoaches = nCoaches;
        notifyAll();
    }
    
    /**
     * This method makes players seat at the bench and wait for the referee.
     * Method blocks until coaches wake players to play.
     * Also it checks if player is in the list of players to play.
     * Method called by players only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param id int - ID of the player that is going to seat at the bench.
     * @return boolean - Returns true if the player after being woken up is going to play or false if not.
     */
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

    /**
     * Method to check if the match has finished.
     * It can be called by players or coaches.
     * 
     * @return boolean - true if the match has finished, false if not.
     */
    @Override
    public synchronized boolean hasMatchFinished() {
        return matchFinish;
    }

    /**
     * Method to set the match has finished.
     * It blocks and waits for coaches to be waiting.
     * Method can be called by the referee only.
     */
    @Override
    public synchronized void setMatchFinish() {
        matchFinish = true;
        while(coachesWaiting != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        notifyAll();
    }

    /**
     * Method increments the number of players that are seated.
     * Does not block and notifies coaches.
     * Method to be called by players only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
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

    /**
     * Method blocks and waits for all players that are playing to be on the playground ready.
     * It's called by coaches only.
     *
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    @Override
    public synchronized void playersReady(String team) {
        if (team.equals("A")){
            while (playersReadyA != nTrialPlayers) {            
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
            while (playersReadyB != nTrialPlayers) {            
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
        int [] array = new int[nTrialPlayers];
        while(i != nTrialPlayers){
            tmp = new Random().nextInt(maxValue);
            if (!contains(array, tmp)){
                array[i] = tmp;
                i++;
            }
        }
        return array;
    }
    
    private void config(){
        ConfigRepository conf = new ConfigRepository();
        BenchConfig settings = conf.getBenchConfig();
        
        nCoaches = settings.getnCoaches();
        nTeamPlayers = settings.getnTeamPlayers();
        nTrialPlayers = settings.getnTrialPlayers();
    }
    
    /**
     * Method called by the referee to wait for all players to be ready before 
     * starting the match.
     */
    @Override
    public synchronized void waitForPlayers() {
        while ((nBenchPlayersA + nBenchPlayersB) != (nTeamPlayers*2)){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Method called by the referee to wait for all coaches to be ready before 
     * starting the match.
     */
    @Override
    public synchronized void waitForCoaches() {
        while(coachesWaiting != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
    }

    /**
     * Method unused in this implementation of the interface.
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
