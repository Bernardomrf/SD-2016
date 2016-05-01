/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.playerState;

/**
 * Interface for the Player interaction with the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IGeneralRepositoryPlayer {
    
    /**
     * Method to change the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Current strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     */
    public void changePlayerState(playerState state, int id, String team, int strength);
    
    /**
     * Method to initializate the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Initial strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     */
    public void initPlayer(playerState state, int strength, int id, String team);
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}
