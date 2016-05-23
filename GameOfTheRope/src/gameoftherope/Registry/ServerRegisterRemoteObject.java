package gameoftherope.Registry;

import gameoftherope.Interfaces.Register;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * This data type instantiates and registers a remote object that enables the
 * registration of other remote objects located in the same or other processing
 * nodes in the local registry service. Communication is based in Java RMI.
 */
public class ServerRegisterRemoteObject {

    /**
     * Main task.
     */

    public static void main(String[] args) {
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
        
        System.out.println("Security installed");

        /* instantiate a registration remote object and generate a stub for it */
        RegisterRemoteObject regEngine = new RegisterRemoteObject(rmiRegHostName, rmiRegPortNumb);
        Register regEngineStub = null;
        int listeningPort = 22131;
        /* it should be set accordingly in each case */

        try {
            regEngineStub = (Register) UnicastRemoteObject.exportObject(regEngine, listeningPort);
        } catch (RemoteException e) {
            System.exit(1);
        }
        //GenericIO.writelnString("Stub was generated!");

        /* register it with the local registry service */
        String nameEntry = "RegisterHandler";
        Registry registry = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.exit(1);
        }
        //GenericIO.writelnString("RMI registry was created!");

        try {
            registry.rebind(nameEntry, regEngineStub);
        } catch (RemoteException e) {
            System.exit(1);
        }
        //GenericIO.writelnString("RegisterRemoteObject object was registered!");
    }
}
