/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the Referee configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Referee are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class RefConfig implements Serializable {
    private int nTrials = 6;
    private int nGames = 3;

    /**  
     * Method to return the number of trials of a game.
     * 
     * @return int - Number of trials of a game.
     */
    public int getNTrials() {
        return nTrials;
    }
    
    /**
     * Method to set the number of trials of a game.
     * 
     * @param n int - Number of trials of a game.
     */
    public void setNTrials(int n) {
        nTrials = n;
    }
    
    /**  
     * Method to return the number of games of a match.
     * 
     * @return int - Number of games of a match.
     */
    public int getNGames() {
        return nGames;
    }
    
    /**
     * Method to set the number of games of a match.
     * 
     * @param n int - Number of games of a match.
     */
    public void setNGames(int n) {
        nGames = n;
    }
}
