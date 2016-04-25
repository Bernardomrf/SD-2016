/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope;

import EntitiesProxy.BenchProxy;
import EntitiesProxy.GeneralRepositoryProxy;
import EntitiesProxy.PlaygroundProxy;
import EntitiesProxy.RefSiteProxy;
import gameoftherope.Entities.Coach;
import gameoftherope.Entities.Player;
import gameoftherope.Entities.Referee;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IGeneralRepositoryCoach;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IGeneralRepositoryRef;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.ConfigRepository;
import java.io.FileNotFoundException;
import java.util.Map;

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
        
        Map<String, Integer> settings = ConfigRepository.getMainConfigs();
        nCoaches = settings.get("nCoaches");
        nPlayers = settings.get("nPlayers");

        //Bench bench = new Bench();
        //Playground playground = new Playground();
        //RefSite refSite = new RefSite();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        RefSiteProxy refSite = new RefSiteProxy();
        GeneralRepositoryProxy repo = new GeneralRepositoryProxy();

        
        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, (IGeneralRepositoryRef) repo);

        ref.setName("Ref");

        Coach[] coach = new Coach[nCoaches];
        for (int i = 0; i < nCoaches; i++) {
            playground = new PlaygroundProxy();
            refSite = new RefSiteProxy();
            bench = new BenchProxy();
            repo = new GeneralRepositoryProxy();
            if (i < 1) {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "A", (IGeneralRepositoryCoach) repo);
            } else {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "B", (IGeneralRepositoryCoach) repo);
            }
            coach[i].setName("Coach" + i);
            coach[i].start();
        }

        Player[] player = new Player[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            playground = new PlaygroundProxy();
            bench = new BenchProxy();
            repo = new GeneralRepositoryProxy();
            if (i < 5) {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "A", i, (IGeneralRepositoryPlayer) repo);
            } else {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "B", i - 5, (IGeneralRepositoryPlayer) repo);
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
