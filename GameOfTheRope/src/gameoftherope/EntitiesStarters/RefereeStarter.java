/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import EntitiesProxy.BenchProxy;
import EntitiesProxy.ConfigProxy;
import EntitiesProxy.PlaygroundProxy;
import EntitiesProxy.RefSiteProxy;
import gameoftherope.Entities.Referee;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefereeStarter {
    public static void main(String[] args) throws FileNotFoundException {
        
        GeneralRepository repo = new GeneralRepository();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        RefSiteProxy refSite = new RefSiteProxy();
        ConfigProxy conf = new ConfigProxy();
        
        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, repo, conf);
        
        ref.start();
        try {
            ref.join();
        } catch (InterruptedException ex) {
        }
    }
}
