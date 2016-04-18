/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.ConfigRepository;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import java.util.Map;

/**
 * Class to implement the bench monitor.
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrf@ua.pt]
 */
public class RefSite implements IRefSiteRef, IRefSiteCoach{
    
    private int nCoaches;
    
    private int aWins;
    private int bWins;
    private int coachesReady;
    
    /**
     * Constructor for RefSite class
     */
    public RefSite(){
        config();
        this.aWins = 0;
        this.bWins = 0;
        coachesReady = 0;
    }

    /**
     * Method to announce new game.
     * It waits for a random number of seconds before the start of the match.
     * Method can be called by the referee only.
     */
    @Override
    public synchronized void announceNewGame() {
        try {
            Thread.sleep((int)(Math.random() * 4000 + 1000));
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Method to declare the game winner.
     * Transition state
     * Method can be called by the referee only.
     */
    @Override
    public synchronized void declareGameWinner() {
    }

    /**
     * Method to declare the match winner.
     * Transition state
     * Method can be called by the referee only.
     */
    @Override
    public synchronized void declareMatchWinner() {
    }

    /**
     * Method to wait for coach signal.
     * It blocks and waits for both coaches signal.
     * Method can be called by the referee only.
     */
    @Override
    public synchronized void waitForCoach() {
        while(coachesReady != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        coachesReady = 0;
    }

    /** 
     * Method does not block and notifies the coaches.
     * It's called by the referee only.
     */
    @Override
    public synchronized void informReferee() {
        coachesReady++;
        notifyAll();
    }
    
    private void config(){
        Map<String, Integer> settings = ConfigRepository.getRefSiteConfigs();
        nCoaches = settings.get("nCoaches");  
    }
}
