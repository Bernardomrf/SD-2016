/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesStarters;

import EntitiesProxy.BenchProxy;
import EntitiesProxy.PlaygroundProxy;
import gameoftherope.Entities.Player;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Regions.ConfigRepository;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 *
 * @author bernardo
 */
public class PlayerStarter {
    public static void main(String[] args) throws FileNotFoundException {
        
        GeneralRepository repo = new GeneralRepository();
        BenchProxy bench = new BenchProxy();
        PlaygroundProxy playground = new PlaygroundProxy();
        
        int nPlayers;
        Map<String, Integer> settings = ConfigRepository.getMainConfigs();
        nPlayers = settings.get("nPlayers");
        
        Player[] player = new Player[nPlayers];
        for (int i = 0; i < nPlayers; i++) {
            playground = new PlaygroundProxy();
            if (i < 5) {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "A", i, repo);
            } else {
                player[i] = new Player((IPlaygroundPlayer) playground, (IBenchPlayer) bench, "B", i - 5, repo);
            }
            player[i].setName("Player" + i);
            player[i].start();
        }
        for (int i = 0; i < nPlayers; i++) {

            try {
                player[i].join();
            } catch (InterruptedException ex) {
            }
        }
    }
}
