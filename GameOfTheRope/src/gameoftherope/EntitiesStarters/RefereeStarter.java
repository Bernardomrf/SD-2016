/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import EntitiesProxy.RefSiteProxy;
import gameoftherope.Entities.Referee;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.Regions.Bench;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.Regions.Playground;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefereeStarter {
    /*public static void main(String[] args) throws FileNotFoundException {
        
        GeneralRepository repo = new GeneralRepository();
        Bench bench = new Bench();
        Playground playground = new Playground();
        
        RefSiteProxy refSite = new RefSiteProxy();
        
        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, repo);
        
        ref.start();
        try {
            ref.join();
        } catch (InterruptedException ex) {
        }
    }*/
    
    public Referee start(GeneralRepository repo, Bench bench, Playground playground, RefSiteProxy refSite){
        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, repo);
        return ref;
    }
}
