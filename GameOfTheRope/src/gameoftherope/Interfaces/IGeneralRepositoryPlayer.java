/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.VectorClock.VectorClock;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Player interaction with the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IGeneralRepositoryPlayer extends Remote{
    
    /**
     * Method to change the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Current strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @param vc
     * @throws java.rmi.RemoteException
     */
    public void changePlayerState(playerState state, int id, String team, int strength, VectorClock vc) throws RemoteException;
    
    /**
     * Method to initializate the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Initial strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @param vc
     * @throws java.rmi.RemoteException
     */
    public void initPlayer(playerState state, int strength, int id, String team, VectorClock vc) throws RemoteException;
    
}
