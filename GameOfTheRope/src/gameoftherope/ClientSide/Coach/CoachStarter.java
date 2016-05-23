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

    public static void main(String args[]) throws NotBoundException {
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
            bench = (IBenchCoach) registry.lookup(nameEntryBench);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        try {
            bench = (IBenchCoach) registry.lookup(nameEntryBench);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }
        
        Coach coach = new Coach((IBenchCoach) bench,(IPlaygroundCoach) playground,(IRefSiteCoach) refSite ,"A", (IGeneralRepositoryCoach)repo, conf);

        /* instantiate the mobile code object to be run remotely
        Bench task = null;
        BigDecimal pi = null;

        try {
            task = new Pi(Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            GenericIO.writelnString("Pi instantiation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        invoke the remote method (run the code at a ComputeEngine remote object)
        try {
            pi = (BigDecimal) (comp.executeTask(task));
        } catch (RemoteException e) {
            GenericIO.writelnString("ComputePi remote invocation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

         print the result*/
        //GenericIO.writelnString(pi.toString());
    }

}
