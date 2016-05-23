/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Player interaction with the Playground.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IPlaygroundPlayer extends Remote{

    /**
     * Method used to stand in position on the playground waiting.
     * @return nTrials - number of trials
     */
    public int standInPosition() throws RemoteException; // Esperar pelo startTrial que espera pelo coach
    
    /**
     * Method used to pull the rope, changes are made to the rope before sleep is performed.
     *
     * @param strenght int - Strength of the player that is pulling the rope.
     * @param team String - A String representing what team the coach belongs to. 
     *                      Valid options are only "A" or "B".
     */
    public void pullTheRope(int strenght, String team) throws RemoteException; // altera uma variavel partilhada incrementando/decrementando dependendo da equipa
    
    /**
     * Method used to represent the state after players play.
     */
    public void iamDone() throws RemoteException; // incrementa uma variavel de jogadores que já acabaram, ultimo faz notify ao arbitro
                            // Muda o estado para o bench
 
}
