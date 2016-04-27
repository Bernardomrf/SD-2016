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
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length != 1){
            System.err.println("Invalid Arguments");
            System.exit(1);
        }
        String team = args[0];
        
        GeneralRepositoryProxy repo = new GeneralRepositoryProxy();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        RefSiteProxy refSite = new RefSiteProxy();
        ConfigProxy conf = new ConfigProxy();
       
        Coach coach = new Coach((IBenchCoach) bench, (IPlaygroundCoach) playground, (IRefSiteCoach) refSite, team, repo, conf);

        coach.start();

        try {
            coach.join();
        } catch (InterruptedException ex) {
        }

    }
}
