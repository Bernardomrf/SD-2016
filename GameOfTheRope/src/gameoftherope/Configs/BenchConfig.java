/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the bench configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Bench are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class BenchConfig implements Serializable{
    private int nCoaches = 2;
    private int nTeamPlayers = 5;
    private int nTrialPlayers = 3;
    private String benchHostName = "localhost";
    private int benchPort = 22131;

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
     */
    public int getnCoaches() {
        return nCoaches;
    }

    /**
     * Method to set the number of coaches.
     * 
     * @param nCoaches int - Number of coaches.
     */
    public void setnCoaches(int nCoaches) {
        this.nCoaches = nCoaches;
    }

    /**  
     * Method to return the number of players of a team.
     * 
     * @return int - number of players in each team.
     */
    public int getnTeamPlayers() {
        return nTeamPlayers;
    }

    /**
     * Method to set the number of players of a team.
     * 
     * @param nTeamPlayers int - number of players in each team.
     */
    public void setnTeamPlayers(int nTeamPlayers) {
        this.nTeamPlayers = nTeamPlayers;
    }

    /**  
     * Method to return the number of team players for a trial.
     * 
     * @return int - number of team players in each trial.
     */
    public int getnTrialPlayers() {
        return nTrialPlayers;
    }

    /**
     * Method to set the number of team players for a trial.
     * 
     * @param nTrialPlayers int - number of team players in each trial.
     */
    public void setnTrialPlayers(int nTrialPlayers) {
        this.nTrialPlayers = nTrialPlayers;
    }

    /**  
     * Method to return the host name.
     * 
     * @return String - Host name.
     */
    public String getBenchHostName() {
        return benchHostName;
    }

    /**
     * Method to set the host name.
     * 
     * @param benchHostName String - Host name.
     */
    public void setBenchHostName(String benchHostName) {
        this.benchHostName = benchHostName;
    }

    /**  
     * Method to return the Bench port.
     * 
     * @return int - Bench port.
     */
    public int getBenchPort() {
        return benchPort;
    }

    /**
     * Method to set the Bench port.
     * 
     * @param benchPort int - Bench port.
     */
    public void setBenchPort(int benchPort) {
        this.benchPort = benchPort;
    }
}
