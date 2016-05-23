package gameoftherope.ServerSide.Bench;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.Register;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This data type instantiates and registers a remote object that will run
 * mobile code. Communication is based in Java RMI.
 */
public class BenchServer {

    /**
     * Main task.
     *
     * @param args
     * @throws java.rmi.NotBoundException
     */

    public static void main(String[] args) throws NotBoundException {
        /* get location of the registry service */

        String rmiRegHostName = "localhost";
        int rmiRegPortNumb = 22130;

        try {
            rmiRegHostName = args[0];
            rmiRegPortNumb = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.err.println("Bad arguments!");
            //System.exit(1);
        }

        /* create and install the security manager */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        Bench bench = new Bench();
        IBenchPlayer benchStubPlayer = null;
        IBenchCoach benchStubCoach = null;
        IBenchRef benchStubRef = null;

        int listeningPort = 22132;
        /* it should be set accordingly in each case */

        try {
            benchStubCoach = (IBenchCoach) UnicastRemoteObject.exportObject(bench, listeningPort);
            benchStubPlayer = (IBenchPlayer) UnicastRemoteObject.exportObject(bench, listeningPort);
            benchStubRef = (IBenchRef) UnicastRemoteObject.exportObject(bench, listeningPort);
        } catch (RemoteException e) {
            System.exit(1);
        }
        //GenericIO.writelnString ("Stub was generated!");

        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        Registry registry = null;
        Register reg = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.exit(1);
        }
        //GenericIO.writelnString ("RMI registry was created!");

        try {
            reg = (Register) registry.lookup(nameEntryBase);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }

        try {
            reg.bind("BenchPlayer", benchStubPlayer);
            reg.bind("BenchCoach", benchStubCoach);
            reg.bind("BenchRef", benchStubRef);
        } catch (RemoteException | AlreadyBoundException e) {
            System.exit(1);
        }
        //GenericIO.writelnString ("ComputeEngine object was registered!");

        //GenericIO.writelnString ("ComputeEngine object was registered!");
    }
}
