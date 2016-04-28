/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import gameoftherope.Entities.Coach;
import gameoftherope.EntitiesProxy.BenchProxy;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.EntitiesProxy.GeneralRepositoryProxy;
import gameoftherope.EntitiesProxy.PlaygroundProxy;
import gameoftherope.EntitiesProxy.RefSiteProxy;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IRefSiteCoach;
import java.io.FileNotFoundException;

/**
 *
 * @author bernardo
 */
public class CoachStarter {

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length != 3){
            System.err.println("Invalid Arguments");
            System.exit(1);
        }
        String team = args[0];
        
        GeneralRepositoryProxy repo = new GeneralRepositoryProxy(args[1],Integer.parseInt(args[2]));
        BenchProxy bench = new BenchProxy(args[1],Integer.parseInt(args[2]));
        PlaygroundProxy playground = new PlaygroundProxy(args[1],Integer.parseInt(args[2]));
        RefSiteProxy refSite = new RefSiteProxy(args[1],Integer.parseInt(args[2]));
        ConfigProxy conf = new ConfigProxy(args[1],Integer.parseInt(args[2]));
       
        Coach coach = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, team, repo, conf);

        coach.start();

        try {
            coach.join();
        } catch (InterruptedException ex) {
        }

    }
}
