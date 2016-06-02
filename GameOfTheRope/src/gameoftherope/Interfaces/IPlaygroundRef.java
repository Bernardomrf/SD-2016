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
 * Interface for the Referee interaction with the Playground.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IPlaygroundRef extends Remote{

    /**
     * Method used to reset current trial and call a new one.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock callTrial(VectorClock vc) throws RemoteException;
    
    /**
     * Method used to start a new trial.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock startTrial(VectorClock vc) throws RemoteException;
                                
    /**
     * Method that blocks until trial is ongoing.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock waitForTrialConclusion(VectorClock vc) throws RemoteException; 
    
    /**
     * Method used to evaluate who won the trial.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock assertTrialDecision(VectorClock vc) throws RemoteException;
    
    /**
     * Method used to check if a knockout occured during the trial.
     * 
     * @return String - Representation of knockout or not and for what team
     * @throws java.rmi.RemoteException
     */
    public String checkKnockout() throws RemoteException;

    /**
     * Method used to return the current position of the rope
     * @return int - Position of the rope
     * @throws java.rmi.RemoteException
     */
    public int getRope() throws RemoteException;
    
    /**
     * Method used to get the number of trial wins if each team.
     * @return int[] - Number of trial wins if each team.
     * @throws java.rmi.RemoteException
     */
    public int[] getWins() throws RemoteException;

    /**
     * Method used to get the number of game wins if each team.
     * @return int[] - Number of wins if each team.
     * @throws java.rmi.RemoteException
     */
    public int[] getGameWins() throws RemoteException;
    
}


