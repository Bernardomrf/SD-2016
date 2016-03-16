/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;

/**
 *
 * @author brunosilva
 */
public class RefSite implements IRefSiteRef, IRefSiteCoach{
    
    private int aWins;
    private int bWins;
    private int coachesReady;
    
    public RefSite(){
        this.aWins = 0;
        this.bWins = 0;
        coachesReady = 0;
    }

    @Override
    public synchronized void announceNewGame() {
        /*try {
            Thread.sleep((int)(Math.random() * 4000 + 1000));
        } catch (InterruptedException ex) {
        }*/
    }

    @Override
    public synchronized void declareGameWinner() {
        // Escreve no Log
        //System.out.print("Jogo Acabou ");
    }

    @Override
    public synchronized void declareMatchWinner() {
        // Escreve no Log e da ordem de suicidio aos outros todos
        //System.out.println("Partida Acabou");
    }

    @Override
    public synchronized void waitForCoach() {
        while(coachesReady != 2){
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        coachesReady = 0;
    }

    @Override
    public synchronized void informReferee() {
        coachesReady++;
        notifyAll();
    }
}
