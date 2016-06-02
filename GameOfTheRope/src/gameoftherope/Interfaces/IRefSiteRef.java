/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.VectorClock.VectorClock;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Referee interaction with the RefSite.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IRefSiteRef extends Remote{
    
    /**
     * Method to wait for coach signal.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock waitForCoach(VectorClock vc) throws RemoteException; 
    
    /**
     * Method to announce new game.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock announceNewGame(VectorClock vc) throws RemoteException; 
    
    /**
     * Method to declare the game winner.
     * @throws java.rmi.RemoteException
     */
    public void declareGameWinner() throws RemoteException; 
    
    /**
     * Method to declare the match winner.
     * @throws java.rmi.RemoteException
     */
    public void declareMatchWinner() throws RemoteException; 
    
}
