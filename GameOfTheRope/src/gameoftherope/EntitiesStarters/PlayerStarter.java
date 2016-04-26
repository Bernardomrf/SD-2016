/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import gameoftherope.EntitiesProxy.BenchProxy;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.EntitiesProxy.PlaygroundProxy;
import gameoftherope.Entities.Player;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;

/**
 *
 * @author bernardo
 */
public class PlayerStarter {
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length != 2){
            System.err.println("Invalid Arguments");
            System.exit(1);
        }
        int i = Integer.parseInt(args[0]);
        String team = args[1];
        
        GeneralRepository repo = new GeneralRepository();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        ConfigProxy conf = new ConfigProxy();
        
        Player player = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, team, i, repo, conf);
        player.start();

        try {
            player.join();
        } catch (InterruptedException ex) {
        }
    }
}
