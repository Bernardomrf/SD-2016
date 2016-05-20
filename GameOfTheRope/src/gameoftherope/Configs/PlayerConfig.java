/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the player configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Player are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlayerConfig implements Serializable {
    private int maxStrength = 4;

    /**  
     * Method to return the maximum strength of a player.
     * 
     * @return int - Strength.
     */
    public int getMaxStrength() {
        return maxStrength;
    }
    
    /**
     * Method to set the maximum strength of a player.
     * 
     * @param max int - Strength.
     */
    public void setMaxStrength(int max) {
        maxStrength = max;
    }
}
