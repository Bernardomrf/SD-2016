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
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IPlayground extends Remote {
    
    /**
     * Method used to wait for the trial to end.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public VectorClock waitForTrial(VectorClock vc) throws RemoteException;
    
    /**
     * Method used to stand in position on the playground waiting.
     * @param vc
     * @return nTrials - number of trials
     * @throws java.rmi.RemoteException
     */
    public Object[] standInPosition(VectorClock vc) throws RemoteException; 
    
    /**
     * Method used to pull the rope, changes are made to the rope before sleep is performed.
     *
     * @param strenght int - Strength of the player that is pulling the rope.
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock pullTheRope(int strenght, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to represent the state after players play.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock iamDone(VectorClock vc) throws RemoteException;
    
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
    public Object[] checkKnockout(VectorClock vc) throws RemoteException;

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
