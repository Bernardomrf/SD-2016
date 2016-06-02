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
public interface IBench extends Remote{
    
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
    
    /**
     * This method makes players seat at the bench and wait for the referee.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param id int - ID of the player that is going to seat at the bench.
     * @param vc
     * @return boolean - Returns true if the player after being woken up is going to play or false if not.
     * @throws java.rmi.RemoteException
     */
    public Object[] seatAtTheBench(String team, int id, VectorClock vc) throws RemoteException; // Fica bloqueado no banco
    
    /**
     * Method increments the number of players that are seated.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock seatDown(String team, VectorClock vc) throws RemoteException;
    
    /**
     * This method makes players ready to play.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock followCoachAdvice(String team, VectorClock vc) throws RemoteException; // passa para o playgound; Jogadores no banco decrementa, jogadores para jogar aumenta
                                    // Variavel independente para cada equipa
    
    /** 
     * Method does not block and notifies the coaches.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock signalCoaches(VectorClock vc) throws RemoteException; //acorda os treinadores
    
    /**
     * Method to set the match has finished.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock setMatchFinish(VectorClock vc) throws RemoteException;
    
    /**
     * Method unused in this implementation of the interface.
     * @throws java.rmi.RemoteException
     */
    public void close() throws RemoteException;
    
    /**
     * Method called by the referee to wait for all players to be ready before 
     * starting the match.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock waitForPlayers(VectorClock vc) throws RemoteException;
    
    /**
     * Method called by the referee to wait for all coaches to be ready before 
     * starting the match.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock waitForCoaches(VectorClock vc) throws RemoteException;
    
}
