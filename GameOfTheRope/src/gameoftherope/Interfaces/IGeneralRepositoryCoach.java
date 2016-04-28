/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.coachState;

/**
 *
 * @author bernardo
 */
public interface IGeneralRepositoryCoach {
    
    /**
     *
     * @param state
     * @param team
     */
    public void changeCoachState(coachState state, String team);
    
    /**
     *
     * @param state
     * @param team
     */
    public void initCoach(coachState state, String team);
    
    /**
     *
     * @param pos
     * @param team
     */
    public void setPlayersPositions(int[] pos, String team);
    
    /**
     *
     */
    public void close();
    
}
