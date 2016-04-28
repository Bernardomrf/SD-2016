/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import gameoftherope.Entities.Referee;
import gameoftherope.EntitiesProxy.BenchProxy;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.EntitiesProxy.GeneralRepositoryProxy;
import gameoftherope.EntitiesProxy.PlaygroundProxy;
import gameoftherope.EntitiesProxy.RefSiteProxy;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import java.io.FileNotFoundException;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefereeStarter {

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 2){
            System.err.println("Invalid Arguments");
            System.exit(1);
        }
        GeneralRepositoryProxy repo = new GeneralRepositoryProxy(args[0], Integer.parseInt(args[1]));
        BenchProxy bench = new BenchProxy(args[0], Integer.parseInt(args[1]));
        PlaygroundProxy playground = new PlaygroundProxy(args[0], Integer.parseInt(args[1]));
        RefSiteProxy refSite = new RefSiteProxy(args[0], Integer.parseInt(args[1]));
        ConfigProxy conf = new ConfigProxy(args[0], Integer.parseInt(args[1]));
        
        Referee ref = new Referee((IRefSiteRef) refSite, (IPlaygroundRef) playground, (IBenchRef) bench, repo, conf);
        
        ref.start();
        try {
            ref.join();
        } catch (InterruptedException ex) {
        }
    }
}
