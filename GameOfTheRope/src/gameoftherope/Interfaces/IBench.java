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
public interface IBench extends Remote{
    
    /**
     * Method blocks and waits for all players to be seated at the bench.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     * @throws java.rmi.RemoteException 
     *                     
     */
    public void reviewNotes(String team) throws RemoteException;  //Esperar que os jogadores se sentem                      
    
    /**
     * This method generates an array of random player numbers to be called to play.
     * Method does not block, and notifies players.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      
     * @return int[] - An array containing the players that will play.
     * @throws java.rmi.RemoteException
     */
    public int [] callContestants(String team) throws RemoteException; //Chamar n jogadores aleatoriamente para a trial e chamar informReferee
    
    /**
     * Method blocks and waits for the referee to come to the bench.
     * @throws java.rmi.RemoteException
     */
    public void waitForRefCommand() throws RemoteException; //Esperar que o árbitro faça callTrial
    
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
     * @throws java.rmi.RemoteException 
     *                      
     */
    public void playersReady(String team) throws RemoteException;
    
    /**
     * This method makes players seat at the bench and wait for the referee.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param id int - ID of the player that is going to seat at the bench.
     * @return boolean - Returns true if the player after being woken up is going to play or false if not.
     * @throws java.rmi.RemoteException
     */
    public boolean seatAtTheBench(String team, int id) throws RemoteException; // Fica bloqueado no banco
    
    /**
     * Method increments the number of players that are seated.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @throws java.rmi.RemoteException
     */
    public void seatDown(String team) throws RemoteException;
    
    /**
     * This method makes players ready to play.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @throws java.rmi.RemoteException
     */
    public void followCoachAdvice(String team) throws RemoteException; // passa para o playgound; Jogadores no banco decrementa, jogadores para jogar aumenta
                                    // Variavel independente para cada equipa
    
    /** 
     * Method does not block and notifies the coaches.
     * @throws java.rmi.RemoteException
     */
    public void signalCoaches() throws RemoteException; //acorda os treinadores
    
    /**
     * Method to set the match has finished.
     * @throws java.rmi.RemoteException
     */
    public void setMatchFinish() throws RemoteException;
    
    /**
     * Method unused in this implementation of the interface.
     * @throws java.rmi.RemoteException
     */
    public void close() throws RemoteException;
    
    /**
     * Method called by the referee to wait for all players to be ready before 
     * starting the match.
     * @throws java.rmi.RemoteException
     */
    public void waitForPlayers() throws RemoteException;
    
    /**
     * Method called by the referee to wait for all coaches to be ready before 
     * starting the match.
     * @throws java.rmi.RemoteException
     */
    public void waitForCoaches() throws RemoteException;
    
}
