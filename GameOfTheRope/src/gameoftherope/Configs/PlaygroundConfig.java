/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the Playground configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Playground are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlaygroundConfig implements Serializable {
    private int totalTrialPlayers = 6;
    private int knockOutForce = 4;
    private int pullTheRopeSleep = 100;
    private int nTrials = 6;
    private int ncoaches = 2;
    private String playgroundHostName = "localhost";
    private int playgroundPort = 22132;

    /**  
     * Method to return the total number of trial players.
     * 
     * @return int - Number of total trial players.
     */
    public int getTotalTrialPlayers() {
        return totalTrialPlayers;
    }

    /**
     * Method to set the total number of trial players.
     * 
     * @param totalTrialPlayers int - Number of total trial players.
     */
    public void setTotalTrialPlayers(int totalTrialPlayers) {
        this.totalTrialPlayers = totalTrialPlayers;
    }

    /**  
     * Method to return the position of rope necessary for knockout.
     * 
     * @return int - Position of rope necessary for knockout.
     */
    public int getKnockOutForce() {
        return knockOutForce;
    }

    /**
     * Method to set position of rope necessary for knockout.
     * 
     * @param knockOutForce int - Position of rope necessary for knockout.
     */
    public void setKnockOutForce(int knockOutForce) {
        this.knockOutForce = knockOutForce;
    }

    /**  
     * Method to return the timeout before a player pull the rope.
     * 
     * @return int - Timeout before a player pull the rope.
     */
    public int getPullTheRopeSleep() {
        return pullTheRopeSleep;
    }

    /**
     * Method to set the timeout before a player pull the rope.
     * 
     * @param pullTheRopeSleep int - Timeout before a player pull the rope.
     */
    public void setPullTheRopeSleep(int pullTheRopeSleep) {
        this.pullTheRopeSleep = pullTheRopeSleep;
    }

    /**  
     * Method to return the Playground host name.
     * 
     * @return String - Host name.
     */
    public String getPlaygroundHostName() {
        return playgroundHostName;
    }

    /**
     * Method to set the Playground host name.
     * 
     * @param playgroundHostName String - Host name.
     */
    public void setPlaygroundHostName(String playgroundHostName) {
        this.playgroundHostName = playgroundHostName;
    }

    /**  
     * Method to return the Playground Port.
     * 
     * @return int - Playground port.
     */
    public int getPlaygroundPort() {
        return playgroundPort;
    }

    /**
     * Method to set the Playground Port.
     * 
     * @param playgroundPort int - Playground port.
     */
    public void setPlaygroundPort(int playgroundPort) {
        this.playgroundPort = playgroundPort;
    }

    /**  
     * Method to return the number trials of each game.
     * 
     * @return int - Number of trials.
     */
    public int getnTrials() {
        return nTrials;
    }

    /**
     * Method to set the number trials of each game.
     * 
     * @param nTrials int - Number of trials.
     */
    public void setnTrials(int nTrials) {
        this.nTrials = nTrials;
    }

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - Number of coaches.
     */
    public int getNcoaches() {
        return ncoaches;
    }

    /**
     * Method to set the number of coaches.
     * 
     * @param ncoaches int - Number of coaches.
     */
    public void setNcoaches(int ncoaches) {
        this.ncoaches = ncoaches;
    }
}
