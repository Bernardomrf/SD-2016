/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Referee;

import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefereeStarter {

    public static void main(String args[]) throws NotBoundException, RemoteException {
        String rmiRegHostName = "l040101-ws01.ua.pt";
        int rmiRegPortNumb = 22130;

        /* look for the remote object by name in the remote host registry */
        String nameEntryBench = "BenchRef";
        IBenchRef bench = null;
        
        String nameEntryPlayground = "PlaygroundRef";
        IPlaygroundRef playground = null;
        
        String nameEntryRefSite = "RefSiteRef";
        IRefSiteRef refSite = null;
        
        String nameEntryGeneral = "GeneralRepoRef";
        IGeneralRepositoryRef generalRepo = null;
        
        String nameEntryConfig = "ConfigRepo";
        IConfigRepository configRepo = null;
        
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.out.println("Error registering");
            System.exit(1);
        }

        try {
            bench = (IBenchRef) registry.lookup(nameEntryBench);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup");
            System.exit(1);
        }
        try {
            playground = (IPlaygroundRef) registry.lookup(nameEntryPlayground);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup");
            System.exit(1);
        }
        try {
            generalRepo = (IGeneralRepositoryRef) registry.lookup(nameEntryGeneral);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup");
            System.exit(1);
        }
        try {
            refSite = (IRefSiteRef) registry.lookup(nameEntryRefSite);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup");
            System.exit(1);
        }
        
        try {
            configRepo = (IConfigRepository) registry.lookup(nameEntryConfig);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error lookup");
            System.exit(1);
        }
        
        Referee ref = new Referee(refSite, playground, bench, generalRepo, configRepo);
        
        ref.start();
        
        try {
            ref.join();
        } catch (InterruptedException ex) {
        }

    }

}
