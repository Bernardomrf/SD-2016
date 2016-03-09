/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope;

import gameoftherope.Entities.Coach;
import gameoftherope.Entities.Player;
import gameoftherope.Entities.Referee;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.Bench;
import gameoftherope.Regions.Playground;
import gameoftherope.Regions.RefSite;


/**
 *
 * @author Bernardo
 */
public class GameOfTheRope extends Thread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        int nCoaches = 2;
        int nPlayers = 10;
        
        Bench bench = new Bench();
        Playground playground = new Playground();
        RefSite refSite = new RefSite();
        
        
        Referee ref =  new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench);
        ref.start();
        
        Coach [] coach =  new Coach [nCoaches];
        for(int i = 0; i<nCoaches; i++){
            if(i<1) coach[i] = new Coach((IBenchCoach) bench,(IPlaygroundCoach) playground,(IRefSiteCoach) refSite ,"A");
            else coach[i] = new Coach((IBenchCoach) bench,(IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "B");
            coach[i].start();
        }
        
        Player [] player = new Player[nPlayers];
        for(int i = 0; i<nPlayers; i++){
            if(i<5) player[i] = new Player((IPlaygroundPlayer) playground,(IBenchPlayer) bench, "A");
            else player[i] = new Player((IPlaygroundPlayer) playground,(IBenchPlayer) bench, "B");
            player[i].start();
        }
        
        
        
        
        for(int i = 0; i<nCoaches; i++){
            try {
                coach[i].join();
            } catch (InterruptedException ex) {
                //Escrever para o log
            }
            System.err.println("Coach Morreu");
        }
        
        
        for(int i = 0; i<nPlayers; i++){
            
            try {
                player[i].join();
            } catch (InterruptedException ex) {
                //Escrever para o log
            }
            System.err.println("Player Morreu");
        }
        try {
            ref.join();
        } catch (InterruptedException ex) {
            //Escrever para o log
        }
        System.err.println("Ref Morreu");
        
    }

    
}
