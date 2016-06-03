package gameoftherope.ServerSide.ConfigRepository;

import gameoftherope.Interfaces.IConfigRepository;
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
public class ConfigRepositoryServer {

    /**
     * Main task.
     *
     * @param args
     * @throws java.rmi.NotBoundException
     */

    public static void main(String[] args) throws NotBoundException {
        /* get location of the registry service */

        String rmiRegHostName = "l040101-ws01.ua.pt";
        int rmiRegPortNumb = 22130;

        /* create and install the security manager */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        ConfigRepository configRepo = new ConfigRepository();
        IConfigRepository configRepoStub = null;

        int listeningPort = 22136;
        /* it should be set accordingly in each case */

        try {
            configRepoStub = (IConfigRepository) UnicastRemoteObject.exportObject(configRepo, listeningPort);
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
            reg.bind("ConfigRepo", configRepoStub);
        } catch (RemoteException | AlreadyBoundException e) {
            System.exit(1);
        }
    }
}
