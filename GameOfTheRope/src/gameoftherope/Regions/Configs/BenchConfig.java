/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions.Configs;

import java.io.Serializable;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class BenchConfig implements Serializable{
    private int nCoaches = 2;
    private int nTeamPlayers = 5;
    private int nTrialPlayers = 3;
    private String benchHostName = "localhost";
    private int benchPort = 22131;

    /**
     * @return the nCoaches
     */
    public int getnCoaches() {
        return nCoaches;
    }

    /**
     * @param nCoaches the nCoaches to set
     */
    public void setnCoaches(int nCoaches) {
        this.nCoaches = nCoaches;
    }

    /**
     * @return the nTeamPlayers
     */
    public int getnTeamPlayers() {
        return nTeamPlayers;
    }

    /**
     * @param nTeamPlayers the nTeamPlayers to set
     */
    public void setnTeamPlayers(int nTeamPlayers) {
        this.nTeamPlayers = nTeamPlayers;
    }

    /**
     * @return the nTrialPlayers
     */
    public int getnTrialPlayers() {
        return nTrialPlayers;
    }

    /**
     * @param nTrialPlayers the nTrialPlayers to set
     */
    public void setnTrialPlayers(int nTrialPlayers) {
        this.nTrialPlayers = nTrialPlayers;
    }

    /**
     * @return the benchHostName
     */
    public String getBenchHostName() {
        return benchHostName;
    }

    /**
     * @param benchHostName the benchHostName to set
     */
    public void setBenchHostName(String benchHostName) {
        this.benchHostName = benchHostName;
    }

    /**
     * @return the benchPort
     */
    public int getBenchPort() {
        return benchPort;
    }

    /**
     * @param benchPort the benchPort to set
     */
    public void setBenchPort(int benchPort) {
        this.benchPort = benchPort;
    }
}
