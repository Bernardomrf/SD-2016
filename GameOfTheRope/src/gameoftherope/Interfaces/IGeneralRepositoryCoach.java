/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.coachState;

/**
 * Interface for the Coach interaction with the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IGeneralRepositoryCoach {
    
    /**
     * Method to change the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     */
    public void changeCoachState(coachState state, String team);
    
    /**
     * Method to initializate the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     */
    public void initCoach(coachState state, String team);
    
    /**
     * Method used to set the players positions for the trial.
     * @param pos int[] - Array containing the players positions.
     * @param team String - Team corresponding to the positions.
     */
    public void setPlayersPositions(int[] pos, String team);
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
    
}
