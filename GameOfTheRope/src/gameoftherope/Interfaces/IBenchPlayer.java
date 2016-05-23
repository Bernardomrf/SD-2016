/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Coach interaction with the Bench.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IBenchPlayer extends Remote{

    /**
     * This method makes players seat at the bench and wait for the referee.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     * @param id int - ID of the player that is going to seat at the bench.
     * @return boolean - Returns true if the player after being woken up is going to play or false if not.
     */
    public boolean seatAtTheBench(String team, int id) throws RemoteException; // Fica bloqueado no banco
    
    /**
     * Method increments the number of players that are seated.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    public void seatDown(String team) throws RemoteException;
    
    /**
     * This method makes players ready to play.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    public void followCoachAdvice(String team) throws RemoteException; // passa para o playgound; Jogadores no banco decrementa, jogadores para jogar aumenta
                                    // Variavel independente para cada equipa
    
    /**
     * Method to check if the match has finished.
     * 
     * @return boolean - true if the match has finished, false if not.
     */
    public boolean hasMatchFinished() throws RemoteException;

}
