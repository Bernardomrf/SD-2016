/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.playerState;

/**
 *
 * @author bernardo
 */
public interface IGeneralRepositoryPlayer {
    
    /**
     *
     * @param state
     * @param id
     * @param team
     * @param strength
     */
    public void changePlayerState(playerState state, int id, String team, int strength);
    
    /**
     *
     * @param state
     * @param strength
     * @param id
     * @param team
     */
    public void initPlayer(playerState state, int strength, int id, String team);
    
    public void close();
}
