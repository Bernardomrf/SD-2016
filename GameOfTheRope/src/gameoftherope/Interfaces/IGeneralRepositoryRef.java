/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.refState;

/**
 *
 * @author bernardo
 */
public interface IGeneralRepositoryRef {
    /**
     *
     * @param state
     */
    public void changeRefState(refState state);
    
    /**
     *
     * @param state
     */
    public void initRef(refState state);
    
    /**
     *
     * @param nGame
     */
    public void newGame(int nGame);
    
    /**
     *
     * @param nTrial
     */
    public void newTrial(int nTrial);
    
    /**
     *
     * @param rope
     */
    public void setRope(int rope);
    
    /**
     *
     * @param wins
     * @param knockout
     */
    public void setWins(int[] wins, String knockout);

    /**
     *
     * @param gameWins
     * @param nGame
     */
    public void setGameWins(int[] gameWins, int nGame);
    
    /**
     *
     */
    public void printHeader();
    
    /**
     *
     */
    public void close();
}
