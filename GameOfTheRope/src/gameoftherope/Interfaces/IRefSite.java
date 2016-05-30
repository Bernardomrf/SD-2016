/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IRefSite extends Remote{
    
    /** 
     * Method does not block and notifies the coaches.
     * @throws java.rmi.RemoteException
     */
    public void informReferee() throws RemoteException;
    
    /**
     * Method to wait for coach signal.
     * @throws java.rmi.RemoteException
     */
    public void waitForCoach() throws RemoteException; 
    
    /**
     * Method to announce new game.
     * @throws java.rmi.RemoteException
     */
    public void announceNewGame() throws RemoteException; 
    
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
