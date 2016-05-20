/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.refState;

/**
 * Interface for the Referee interaction with the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IGeneralRepositoryRef {
    /**
     * Method to change the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     */
    public void changeRefState(refState state);
    
    /**
     * Method to initializate the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     */
    public void initRef(refState state);
    
    /**
     * Method used to represent that a new trial has started.
     * @param nGame int - Number of the new game.
     */
    public void newGame(int nGame);
    
    /**
     * Method used to represent that a new trial has started.
     * @param nTrial int - Number of the new trial.
     */
    public void newTrial(int nTrial);
    
    /**
     * Method to set the rope value.
     * @param rope int - The rope value.
     */
    public void setRope(int rope);
    
    /**
     * Method used to set the trial wins.
     * @param wins int[] - Trial wins.
     * @param knockout String - Representing if a knockout existed in that trial.
     */
    public void setWins(int[] wins, String knockout);

    /**
     * Method used to set the game wins.
     * @param gameWins int[] - Wins for both teams.
     * @param nGame int - Number of the game the wins are refering.
     */
    public void setGameWins(int[] gameWins, int nGame);
    
    /**
     * Method used to print the log file header.
     */
    public void printHeader();
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}
