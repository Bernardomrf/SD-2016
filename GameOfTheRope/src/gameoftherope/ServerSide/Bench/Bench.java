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
import gameoftherope.VectorClock.VectorClock;
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

    private final VectorClock clocks;
    
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
        
        clocks = new VectorClock(13, 0);
    }
    
    /**  
     * Method blocks and waits for all players to be seated at the bench.
     * It's called by coaches only.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    @Override
    public synchronized VectorClock reviewNotes(String team, VectorClock vc) {
        clocks.update(vc);
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
        return clocks.clone();
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
    public synchronized Object[] callContestants(String team, VectorClock vc) {
        Object[] returns = new Object[2];
        if (team.equals("A")){
            callPlayersA = nTrialPlayers;
            playersToPlayA = generateRandom(nTeamPlayers);
            notifyAll();
            returns[0] = playersToPlayA;
        }
        else if (team.equals("B")){
            callPlayersB = nTrialPlayers;
            playersToPlayB = generateRandom(nTeamPlayers);
            notifyAll();
            returns[0] = playersToPlayB;
        }
        returns[1] = clocks.clone();
        return returns;
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
    public synchronized VectorClock followCoachAdvice(String team, VectorClock vc) {
        clocks.update(vc);
        if (team.equals("A")){
            playersReadyA++;
        }
        else if (team.equals("B")){
            playersReadyB++;
        }
        notifyAll();
        return clocks.clone();
    }

    /**
     * Method blocks and waits for the referee to come to the bench.
     * It's called by coaches only. 
     */
    @Override
    public synchronized VectorClock waitForRefCommand(VectorClock vc) {
        clocks.update(vc);
        coachesWaiting++;
        notifyAll();
        while(wakeCoaches == 0){
            try {
                wait();
                if (matchFinish){
                    return clocks.clone();
                }
            } catch (InterruptedException ex) {
            }
        }
        wakeCoaches--;
        coachesWaiting--;
        return clocks.clone();
    }
    
    /** 
     * Method does not block and notifies the coaches.
     * It's called by the referee only.
     */
    @Override
    public synchronized VectorClock signalCoaches(VectorClock vc){
        clocks.update(vc);
        wakeCoaches = nCoaches;
        notifyAll();
        return clocks.clone();
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
    public synchronized Object[] seatAtTheBench(String team, int id, VectorClock vc) {
        clocks.update(vc);
        Object[] returns = new Object[2];
        if (team.equals("A")){
            while(callPlayersA == 0){
                try {
                    wait();
                    if (matchFinish){
                        returns[0] = false;
                        returns[1] = clocks.clone();
                        return returns;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayA, id)){
                    if (callPlayersA != 0){
                        nBenchPlayersA--;
                        returns[0] = false;
                        returns[1] = clocks.clone();
                        return returns;
                    }
                }
            }
            callPlayersA--;
            nBenchPlayersA--;
            returns[0] = true;
            returns[1] = clocks.clone();
            return returns;
        }
        else if (team.equals("B")){
            while(callPlayersB == 0){
                try {
                    wait();
                    if (matchFinish){
                        returns[0] = false;
                        returns[1] = clocks.clone();
                        return returns;
                    }
                } catch (InterruptedException ex) {}
                if(!contains(playersToPlayB, id)){
                    if (callPlayersB != 0){
                        nBenchPlayersB--;
                        returns[0] = false;
                        returns[1] = clocks.clone();
                        return returns;
                    }
                }
            }
            callPlayersB--;
            nBenchPlayersB--;
            returns[0] = true;
            returns[1] = clocks.clone();
            return returns;
        }
        returns[0] = false;
        returns[1] = clocks.clone();
        return returns;
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
    public synchronized VectorClock setMatchFinish(VectorClock vc) {
        clocks.update(vc);
        matchFinish = true;
        while(coachesWaiting != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        notifyAll();
        return clocks.clone();
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
    public synchronized VectorClock seatDown(String team, VectorClock vc) {
        clocks.update(vc);
        if (team.equals("A")){
            nBenchPlayersA++;
        }
        else if (team.equals("B")){
            nBenchPlayersB++;
        }
        notifyAll();
        return clocks.clone();
    }

    /**
     * Method blocks and waits for all players that are playing to be on the playground ready.
     * It's called by coaches only.
     *
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    @Override
    public synchronized VectorClock playersReady(String team, VectorClock vc) {
        clocks.update(vc);
        if (team.equals("A")){
            while (playersReadyA != nTrialPlayers) {            
                try {
                    wait();
                    if (matchFinish){
                        return clocks.clone();
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
                        return clocks.clone();
                    }
                } catch (InterruptedException ex) {}
            }
            playersReadyB = 0;
        }
        return clocks.clone();
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
