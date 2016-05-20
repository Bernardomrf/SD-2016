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
     * Method to set the number of coaches.
     * 
     * @param nCoaches int - Number of coaches.
     */
    public void setnCoaches(int nCoaches) {
        this.nCoaches = nCoaches;
    }

    /**  
     * Method to return the number of players.
     * 
     * @return int - number of players.
     */
    public int getnPlayers() {
        return nPlayers;
    }

    /**
     * Method to set the number of plyers.
     * 
     * @param nPlayers int - Number of players.
     */
    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }

    /**  
     * Method to return the General Repository Host Name.
     * 
     * @return String - Host name.
     */
    public String getGeneralRepositoryHostName() {
        return generalRepositoryHostName;
    }

    /**
     * Method to set the General Repository Host Name.
     * 
     * @param generalRepositoryHostName String - Host name.
     */
    public void setGeneralRepositoryHostName(String generalRepositoryHostName) {
        this.generalRepositoryHostName = generalRepositoryHostName;
    }

    /**  
     * Method to return the General Repository Port Number.
     * 
     * @return int - Port number.
     */
    public int getGeneralRepositoryPort() {
        return generalRepositoryPort;
    }

    /**
     * Method to set the General Repository Port Number.
     * 
     * @param generalRepositoryPort int - Port number.
     */
    public void setGeneralRepositoryPort(int generalRepositoryPort) {
        this.generalRepositoryPort = generalRepositoryPort;
    }

    /**  
     * Method to return the number of players of a team.
     * 
     * @return int - Players of a Team.
     */
    public int getnTeamPlayers() {
        return nTeamPlayers;
    }

    /**
     * Method to set the number of players of a team.
     * 
     * @param nTeamPlayers int - Players of a Team.
     */
    public void setnTeamPlayers(int nTeamPlayers) {
        this.nTeamPlayers = nTeamPlayers;
    }

    /**  
     * Method to return the number of trial players.
     * 
     * @return int - number trial players.
     */
    public int getNtrialPlayers() {
        return ntrialPlayers;
    }

    /**
     * Method to set the number of trial players.
     * 
     * @param ntrialPlayers int - number trial players.
     */
    public void setNtrialPlayers(int ntrialPlayers) {
        this.ntrialPlayers = ntrialPlayers;
    }
}
