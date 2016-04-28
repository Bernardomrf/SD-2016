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
public class RefConfig implements Serializable {
    private int nTrials = 6;
    private int nGames = 3;

    /**
     *
     * @return
     */
    public int getNTrials() {
        return nTrials;
    }
    
    /**
     *
     * @param n
     */
    public void setNTrials(int n) {
        nTrials = n;
    }
    
    /**
     *
     * @return
     */
    public int getNGames() {
        return nGames;
    }
    
    /**
     *
     * @param n
     */
    public void setNGames(int n) {
        nGames = n;
    }
}
