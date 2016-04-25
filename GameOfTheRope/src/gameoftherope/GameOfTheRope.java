/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope;

import EntitiesProxy.BenchProxy;
import EntitiesProxy.ConfigProxy;
import EntitiesProxy.PlaygroundProxy;
import EntitiesProxy.RefSiteProxy;
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
import gameoftherope.Regions.Configs.GeneralRepositoryConfig;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;

/**
 *
 * @author Bernardo
 */
public class GameOfTheRope extends Thread {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        int nCoaches;
        int nPlayers;
        
        //Bench bench = new Bench();
        //Playground playground = new Playground();
        //RefSite refSite = new RefSite();
        ConfigProxy conf = new ConfigProxy();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        RefSiteProxy refSite = new RefSiteProxy();
        GeneralRepository repo = new GeneralRepository();
        
        GeneralRepositoryConfig grc = conf.getGeneralRepositoryConfig();
        nCoaches = grc.getnCoaches();
        nPlayers = grc.getnPlayers();

        repo.printHeader();

        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, repo, conf);

        ref.setName("Ref");

        Coach[] coach = new Coach[nCoaches];
        for (int i = 0; i < nCoaches; i++) {
            conf = new ConfigProxy();
            playground = new PlaygroundProxy();
            refSite = new RefSiteProxy();
            bench = new BenchProxy();
            
            if (i < 1) {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "A", repo, conf);
            } else {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "B", repo, conf);
            }
            coach[i].setName("Coach" + i);
            coach[i].start();
        }

        Player[] player = new Player[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            conf = new ConfigProxy();
            playground = new PlaygroundProxy();
            bench = new BenchProxy();
            
            if (i < 5) {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "A", i, repo, conf);
            } else {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "B", i - 5, repo, conf);
            }
            player[i].setName("Player" + i);
            player[i].start();
        }

        ref.start();

        for (int i = 0; i < nCoaches; i++) {
            try {
                coach[i].join();
            } catch (InterruptedException ex) {
            }
        }

        for (int i = 0; i < nPlayers; i++) {

            try {
                player[i].join();
            } catch (InterruptedException ex) {
            }
        }

        try {
            ref.join();
        } catch (InterruptedException ex) {
        }
    }
}
