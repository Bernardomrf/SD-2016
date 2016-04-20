/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import EntitiesProxy.BenchProxy;
import EntitiesProxy.PlaygroundProxy;
import EntitiesProxy.RefSiteProxy;
import gameoftherope.ConfigRepository;
import gameoftherope.Entities.Coach;
import gameoftherope.Entities.Referee;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.Bench;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.Regions.Playground;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 *
 * @author bernardo
 */
public class CoachStarter {
    public static void main(String[] args) throws FileNotFoundException {
        
        GeneralRepository repo = new GeneralRepository();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        RefSiteProxy refSite = new RefSiteProxy();
        
        int nCoaches;
        
        Map<String, Integer> settings = ConfigRepository.getMainConfigs();
        nCoaches = settings.get("nCoaches");
        
        Coach[] coach = new Coach[nCoaches];
        for (int i = 0; i < nCoaches; i++) {
            playground = new PlaygroundProxy();
            refSite = new RefSiteProxy();
            bench = new BenchProxy();
            if (i < 1) {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "A", repo);
            } else {
                coach[i] = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, "B", repo);
            }
            coach[i].setName("Coach" + i);
            coach[i].start();
        }
        for (int i = 0; i < nCoaches; i++) {
            try {
                coach[i].join();
            } catch (InterruptedException ex) {
            }
        }
    }
}
