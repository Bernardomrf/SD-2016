/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;

/**
 * Class to implement the bench monitor.
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrf@ua.pt]
 */
public class RefSite implements IRefSiteRef, IRefSiteCoach{
    
    private int nCoaches;
    private int coachesReady;
    
    /**
     * Constructor for RefSite class
     */
    public RefSite(String configHostName, int portNum){
        config(configHostName, portNum);
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
    
    private void config(String configHostName, int portNum){
        ConfigProxy conf = new ConfigProxy(configHostName, portNum);
        RefSiteConfig settings = conf.getRefSiteConfig();
        nCoaches = settings.getnCoaches();
    }

    /**
     * Method unused in this implementation of the interface.
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
