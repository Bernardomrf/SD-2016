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
public interface IPlayground extends Remote {
    
    /**
     * Method used to wait for the trial to end.
     */
    public void waitForTrial() throws RemoteException;
    
    /**
     * Method used to stand in position on the playground waiting.
     * @return nTrials - number of trials
     */
    public int standInPosition() throws RemoteException; 
    
    /**
     * Method used to pull the rope, changes are made to the rope before sleep is performed.
     *
     * @param strenght int - Strength of the player that is pulling the rope.
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    public void pullTheRope(int strenght, String team) throws RemoteException;
    
    /**
     * Method used to represent the state after players play.
     */
    public void iamDone() throws RemoteException;
    
    /**
     * Method used to reset current trial and call a new one.
     */
    public void callTrial() throws RemoteException;
    
    /**
     * Method used to start a new trial.
     */
    public void startTrial() throws RemoteException;
                                
    /**
     * Method that blocks until trial is ongoing.
     */
    public void waitForTrialConclusion() throws RemoteException; 
    
    /**
     * Method used to evaluate who won the trial.
     */
    public void assertTrialDecision() throws RemoteException;
    
    /**
     * Method used to check if a knockout occured during the trial.
     * 
     * @return String - Representation of knockout or not and for what team
     */
    public String checkKnockout() throws RemoteException;

    /**
     * Method used to return the current position of the rope
     * @return int - Position of the rope
     */
    public int getRope() throws RemoteException;
    
    /**
     * Method used to get the number of trial wins if each team.
     * @return int[] - Number of trial wins if each team.
     */
    public int[] getWins() throws RemoteException;

    /**
     * Method used to get the number of game wins if each team.
     * @return int[] - Number of wins if each team.
     */
    public int[] getGameWins() throws RemoteException;
}
