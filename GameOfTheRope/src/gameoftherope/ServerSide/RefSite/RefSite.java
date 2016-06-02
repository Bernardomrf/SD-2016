/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.RefSite;

import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.Interfaces.IRefSite;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.ServerSide.ConfigRepository.ConfigRepository;
import gameoftherope.VectorClock.VectorClock;

/**
 * Class to implement the bench monitor.
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrf@ua.pt]
 */
public class RefSite implements IRefSiteRef, IRefSiteCoach, IRefSite{
    
    private int nCoaches;
    private int coachesReady;
    
    private final VectorClock clocks;
    
    /**
     * Constructor for RefSite class
     * @param configHostName - Host name for configs
     * @param portNum - Port number for configs
     */
    public RefSite(){
        config();
        coachesReady = 0;
        
        clocks = new VectorClock(13, 0);
    }

    /**
     * Method to announce new game.
     * It waits for a random number of seconds before the start of the match.
     * Method can be called by the referee only.
     */
    @Override
    public synchronized VectorClock announceNewGame(VectorClock vc) {
        clocks.update(vc);
        try {
            Thread.sleep((int)(Math.random() * 4000 + 1000));
        } catch (InterruptedException ex) {
        }
        return clocks.clone();
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
    public synchronized VectorClock waitForCoach(VectorClock vc) {
        clocks.update(vc);
        while(coachesReady != nCoaches){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        coachesReady = 0;
        return clocks.clone();
    }

    /** 
     * Method does not block and notifies the coaches.
     * It's called by the referee only.
     */
    @Override
    public synchronized VectorClock informReferee(VectorClock vc) {
        clocks.update(vc);
        coachesReady++;
        notifyAll();
        return clocks.clone();
    }
    
    private void config(){
        ConfigRepository conf = new ConfigRepository();
        RefSiteConfig settings = conf.getRefSiteConfig();
        nCoaches = settings.getnCoaches();
    }
}
