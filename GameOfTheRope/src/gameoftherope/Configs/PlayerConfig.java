/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class PlayerConfig implements Serializable {
    private int maxStrength = 4;

    /**
     *
     * @return
     */
    public int getMaxStrength() {
        return maxStrength;
    }
    
    /**
     *
     * @param max
     */
    public void setMaxStrength(int max) {
        maxStrength = max;
    }
}
