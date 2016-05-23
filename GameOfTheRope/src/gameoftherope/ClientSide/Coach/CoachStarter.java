/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Coach;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IRefSiteCoach;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class CoachStarter {

    public static void main(String args[]) throws NotBoundException, RemoteException {
        String rmiRegHostName = "localhost";
        int rmiRegPortNumb = 22130;
        
        String team = "A";

        try {
            rmiRegHostName = args[0];
            rmiRegPortNumb = Integer.parseInt(args[1]);
            team = args[2];
        } catch (Exception e) {
            System.err.println("Bad arguments!");
            //System.exit(1);
        }


        /* look for the remote object by name in the remote host registry */
        String nameEntryBench = "BenchCoach";
        IBenchCoach bench = null;
        
        String nameEntryPlayground = "PlaygroundCoach";
        IPlaygroundCoach playground = null;
        
        String nameEntryRefSite = "RefSiteCoach";
        IRefSiteCoach refSite = null;
        
        String nameEntryGeneral = "GeneralRepoCoach";
        IGeneralRepositoryCoach generalRepo = null;
        
        String nameEntryConfig = "ConfigRepoCoach";
        IConfigRepository configRepo = null;
        
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.exit(1);
        }

        try {
            bench = (IBenchCoach) registry.lookup(nameEntryBench);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        try {
            playground = (IPlaygroundCoach) registry.lookup(nameEntryPlayground);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        try {
            refSite = (IRefSiteCoach) registry.lookup(nameEntryRefSite);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        try {
            generalRepo = (IGeneralRepositoryCoach) registry.lookup(nameEntryGeneral);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        try {
            configRepo = (IConfigRepository) registry.lookup(nameEntryConfig);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        
        Coach coach = new Coach(bench, playground, refSite , team, generalRepo, configRepo);
        
        coach.start();
        
        try {
            coach.join();
        } catch (InterruptedException ex) {
        }

    }

}
