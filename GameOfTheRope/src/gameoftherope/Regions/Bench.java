/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;

/**
 *
 * @author brunosilva
 */
public class Bench implements IBenchCoach, IBenchPlayer{
    
    private int nBenchPlayers;
    
    public Bench(){
        nBenchPlayers = 10;
    }
    

    @Override
    public synchronized void reviewNotes(String team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public synchronized void callContestants(String team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void followCoachAdvice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void waitForRefCommand() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void seatAtTheBench() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
}
