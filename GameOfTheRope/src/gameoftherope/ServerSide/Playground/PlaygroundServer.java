/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.Playground;

import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.Register;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author bernardo
 */
public class PlaygroundServer {

    public static void main(String[] args) throws NotBoundException {
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
        Playground playground = new Playground();
        IPlaygroundPlayer playgroundStubPlayer = null;
        IPlaygroundCoach playgroundStubCoach = null;
        IPlaygroundRef playgroundStubRef = null;

        int listeningPort = 22133;
        /* it should be set accordingly in each case */

        try {
            playgroundStubCoach = (IPlaygroundCoach) UnicastRemoteObject.exportObject(playground, listeningPort);
            playgroundStubPlayer = (IPlaygroundPlayer) UnicastRemoteObject.exportObject(playground, listeningPort);
            playgroundStubRef = (IPlaygroundRef) UnicastRemoteObject.exportObject(playground, listeningPort);
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
            reg.bind("PlaygroundPlayer", playgroundStubPlayer);
            reg.bind("PlaygroundCoach", playgroundStubCoach);
            reg.bind("PlaygroundRef", playgroundStubRef);
        } catch (RemoteException | AlreadyBoundException e) {
            System.exit(1);
        }
        //GenericIO.writelnString ("ComputeEngine object was registered!");

        //GenericIO.writelnString ("ComputeEngine object was registered!");
    }
}
