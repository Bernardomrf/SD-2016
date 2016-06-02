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
 * Interface for the Coach interaction with the Bench.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IBenchCoach extends Remote{

    /**
     * Method blocks and waits for all players to be seated at the bench.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     * @param vc 
     * @return  
     * @throws java.rmi.RemoteException  
     *                     
     */
    public VectorClock reviewNotes(String team, VectorClock vc) throws RemoteException;  //Esperar que os jogadores se sentem                      
    
    /**
     * This method generates an array of random player numbers to be called to play.
     * Method does not block, and notifies players.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     * @param vc 
     *                      
     * @return int[] - An array containing the players that will play.
     * @throws java.rmi.RemoteException
     */
    public Object[] callContestants(String team, VectorClock vc) throws RemoteException; //Chamar n jogadores aleatoriamente para a trial e chamar informReferee
    
    /**
     * Method blocks and waits for the referee to come to the bench.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock waitForRefCommand(VectorClock vc) throws RemoteException; //Esperar que o árbitro faça callTrial
    
    /**
     * Method to check if the match has finished.
     * 
     * @return boolean - true if the match has finished, false if not.
     * @throws java.rmi.RemoteException
     */
    public boolean hasMatchFinished() throws RemoteException;
    
    /**
     * It's called by coaches only.
     *
     * @param team String - A String representing what team the coach belongs to. 
     * @param vc 
     * @return  
     * @throws java.rmi.RemoteException 
     *                      
     */
    public VectorClock playersReady(String team, VectorClock vc) throws RemoteException;
    
}
