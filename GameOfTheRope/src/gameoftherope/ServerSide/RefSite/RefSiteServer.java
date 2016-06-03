/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.RefSite;

import gameoftherope.Interfaces.IRefSite;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
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
public class RefSiteServer {
    public static void main(String[] args) throws NotBoundException {
        String rmiRegHostName = "l040101-ws01.ua.pt";
        int rmiRegPortNumb = 22130;

        /* create and install the security manager */
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager was installed!");

        /* instantiate a remote object that runs mobile code and generate a stub for it */
        RefSite refSite = new RefSite();
        IRefSite refSiteStub = null;
        IRefSiteCoach refSiteStubCoach = null;
        IRefSiteRef refSiteStubRef = null;

        int listeningPort = 22134;
        /* it should be set accordingly in each case */

        try {
            refSiteStub = (IRefSite) UnicastRemoteObject.exportObject(refSite, listeningPort);
            refSiteStubCoach = (IRefSiteCoach) refSiteStub;
            refSiteStubRef = (IRefSiteRef) refSiteStub;
        } catch (RemoteException e) {
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
            System.exit(1);
        }
        System.out.println("RMI registry was created!");

        try {
            reg = (Register) registry.lookup(nameEntryBase);
        } catch (RemoteException | NotBoundException e) {
            System.exit(1);
        }

        try {
            reg.bind("RefSiteCoach", refSiteStubCoach);
            reg.bind("RefSiteRef", refSiteStubRef);
        } catch (RemoteException | AlreadyBoundException e) {
            System.exit(1);
        }
        System.out.println("ComputeEngine object was registered!");

        //GenericIO.writelnString ("ComputeEngine object was registered!");
    }
}
