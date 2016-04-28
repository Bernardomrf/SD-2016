/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the General Repository configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the General Repository are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class GeneralRepositoryConfig implements Serializable {
    private int nCoaches = 2;
    private int nPlayers = 10;
    private int nTeamPlayers = 5;
    private int ntrialPlayers = 3;
    private String generalRepositoryHostName = "localhost";
    private int generalRepositoryPort = 22130;

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
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
     * Method to return the number of players.
     * 
     * @return int - number of coaches.
     */
    public int getnPlayers() {
        return nPlayers;
    }

    /**
     * @param nPlayers the nPlayers to set
     */
    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
     */
    public String getGeneralRepositoryHostName() {
        return generalRepositoryHostName;
    }

    /**
     * @param generalRepositoryHostName the generalRepositoryHostName to set
     */
    public void setGeneralRepositoryHostName(String generalRepositoryHostName) {
        this.generalRepositoryHostName = generalRepositoryHostName;
    }

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
     */
    public int getGeneralRepositoryPort() {
        return generalRepositoryPort;
    }

    /**
     * @param generalRepositoryPort the generalRepositoryPort to set
     */
    public void setGeneralRepositoryPort(int generalRepositoryPort) {
        this.generalRepositoryPort = generalRepositoryPort;
    }

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
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
     * Method to return the number of coaches.
     * 
     * @return int - number of coaches.
     */
    public int getNtrialPlayers() {
        return ntrialPlayers;
    }

    /**
     * @param ntrialPlayers the ntrialPlayers to set
     */
    public void setNtrialPlayers(int ntrialPlayers) {
        this.ntrialPlayers = ntrialPlayers;
    }
}
