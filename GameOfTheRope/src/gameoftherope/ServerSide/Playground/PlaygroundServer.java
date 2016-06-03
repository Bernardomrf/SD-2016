/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.Playground;

import gameoftherope.Interfaces.IPlayground;
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
            for(int i = 0; i < args.length; i++){
                System.out.println(args[i]);
            }
            rmiRegHostName = args[0];
            rmiRegPortNumb = Integer.parseInt(args[1]);
        } catch (Exception e) {
            e.printStackTrace();
            //System.exit(1);
        }

        /* create and install the security manager */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        Playground playground = new Playground();
        IPlayground playgroundStub = null;
        IPlaygroundPlayer playgroundStubPlayer = null;
        IPlaygroundCoach playgroundStubCoach = null;
        IPlaygroundRef playgroundStubRef = null;

        int listeningPort = 22133;
        /* it should be set accordingly in each case */

        try {
            playgroundStub = (IPlayground) UnicastRemoteObject.exportObject(playground, listeningPort);
            playgroundStubCoach = (IPlaygroundCoach) playgroundStub;
            playgroundStubPlayer = (IPlaygroundPlayer) playgroundStub;
            playgroundStubRef = (IPlaygroundRef) playgroundStub;
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Error Creating stub");
            System.exit(1);
        }
        System.out.println("Stub was generated!");

        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        Registry registry = null;
        Register reg = null;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            System.out.println("Error creating RMI");
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try {
            reg = (Register) registry.lookup(nameEntryBase);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Error looking up");
            System.exit(1);
        }

        try {
            reg.bind("PlaygroundPlayer", playgroundStubPlayer);
            reg.bind("PlaygroundCoach", playgroundStubCoach);
            reg.bind("PlaygroundRef", playgroundStubRef);
        } catch (RemoteException | AlreadyBoundException e) {
            System.out.println("Error binding");
            System.exit(1);
        }
        System.out.println("Playground object was registered!");
    }
}
