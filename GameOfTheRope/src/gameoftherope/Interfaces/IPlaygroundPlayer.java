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
 * Interface for the Player interaction with the Playground.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IPlaygroundPlayer extends Remote{

    /**
     * Method used to stand in position on the playground waiting.
     * @param vc
     * @return nTrials - number of trials
     * @throws java.rmi.RemoteException
     */
    public Object[] standInPosition(VectorClock vc) throws RemoteException; // Esperar pelo startTrial que espera pelo coach
    
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
    public VectorClock pullTheRope(int strenght, String team, VectorClock vc) throws RemoteException; // altera uma variavel partilhada incrementando/decrementando dependendo da equipa
    
    /**
     * Method used to represent the state after players play.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock iamDone(VectorClock vc) throws RemoteException; // incrementa uma variavel de jogadores que já acabaram, ultimo faz notify ao arbitro
                            // Muda o estado para o bench
 
}
