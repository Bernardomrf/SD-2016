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
public class RefConfig implements Serializable {
    private int nTrials = 6;
    private int nGames = 3;

    public int getNTrials() {
        return nTrials;
    }
    
    public void setNTrials(int n) {
        nTrials = n;
    }
    
    public int getNGames() {
        return nGames;
    }
    
    public void setNGames(int n) {
        nGames = n;
    }
}
