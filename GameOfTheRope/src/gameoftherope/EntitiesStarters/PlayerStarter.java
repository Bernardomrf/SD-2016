/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import gameoftherope.Entities.Player;
import gameoftherope.EntitiesProxy.BenchProxy;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.EntitiesProxy.GeneralRepositoryProxy;
import gameoftherope.EntitiesProxy.PlaygroundProxy;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import java.io.FileNotFoundException;

/**
 * Main class to allow execution of a player
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlayerStarter {

    /**
     * Main method for the player starter.
     * @param args String[] - Args required: player id, player team, config server hostname, config server port.
     * @throws FileNotFoundException - Exception for file not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        if(args.length != 4){
            System.err.println("Invalid Arguments");
            System.exit(1);
        }
        int i = Integer.parseInt(args[0]);
        String team = args[1];
        
        GeneralRepositoryProxy repo = new GeneralRepositoryProxy(args[2],Integer.parseInt(args[3]));
        BenchProxy bench = new BenchProxy(args[2],Integer.parseInt(args[3]));
        PlaygroundProxy playground = new PlaygroundProxy(args[2],Integer.parseInt(args[3]));
        ConfigProxy conf = new ConfigProxy(args[2],Integer.parseInt(args[3]));
        
        Player player = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, team, i, repo, conf);
        player.start();

        try {
            player.join();
        } catch (InterruptedException ex) {
        }
    }
}
