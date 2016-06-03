/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Player;

import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class PlayerStarter {

    public static void main(String args[]) throws NotBoundException, RemoteException {
        String rmiRegHostName = "l040101-ws01.ua.pt";
        int rmiRegPortNumb = 22130;
        
        String team = "A";
        int id = 0;

        try {
            rmiRegHostName = args[0];
            rmiRegPortNumb = Integer.parseInt(args[1]);
            team = args[2];
            id = Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.err.println("Bad arguments!");
            //System.exit(1);
        }


        /* look for the remote object by name in the remote host registry */
        String nameEntryBench = "BenchPlayer";
        IBenchPlayer bench = null;
        
        String nameEntryPlayground = "PlaygroundPlayer";
        IPlaygroundPlayer playground = null;
        
        String nameEntryGeneral = "GeneralRepoPlayer";
        IGeneralRepositoryPlayer generalRepo = null;
        
        String nameEntryConfig = "ConfigRepo";
        IConfigRepository configRepo = null;
        
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.out.println("Error registering player");
            System.exit(1);
        }

        try {
            bench = (IBenchPlayer) registry.lookup(nameEntryBench);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup bench player");
            System.exit(1);
        }
        try {
            playground = (IPlaygroundPlayer) registry.lookup(nameEntryPlayground);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup playground player");
            System.exit(1);
        }
        try {
            generalRepo = (IGeneralRepositoryPlayer) registry.lookup(nameEntryGeneral);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup general player");
            System.exit(1);
        }
        try {
            configRepo = (IConfigRepository) registry.lookup(nameEntryConfig);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup config player");
            System.exit(1);
        }
        
        Player coach = new Player(playground, bench, team, id, generalRepo, configRepo);
        
        coach.start();
        
        try {
            coach.join();
        } catch (InterruptedException ex) {
        }

    }

}
