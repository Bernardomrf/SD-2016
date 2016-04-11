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
 *
 * @author brunosilva
 */
public class RefSite implements IRefSiteRef, IRefSiteCoach{
    
    private int nCoaches;
    
    private int aWins;
    private int bWins;
    private int coachesReady;
    
    /**
     *
     */
    public RefSite(){
        config();
        this.aWins = 0;
        this.bWins = 0;
        coachesReady = 0;
    }

    /**
     *
     */
    @Override
    public synchronized void announceNewGame() {
        try {
            Thread.sleep((int)(Math.random() * 4000 + 1000));
        } catch (InterruptedException ex) {
        }
    }

    /**
     *
     * @param knockOut
     */
    @Override
    public synchronized void declareGameWinner(String knockOut) {
    }

    /**
     *
     */
    @Override
    public synchronized void declareMatchWinner() {
    }

    /**
     *
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
     *
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
