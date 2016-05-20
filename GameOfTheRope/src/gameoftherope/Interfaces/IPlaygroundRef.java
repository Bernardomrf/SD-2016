/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Referee interaction with the Playground.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IPlaygroundRef {

    /**
     * Method used to reset current trial and call a new one.
     */
    public void callTrial();
    
    /**
     * Method used to start a new trial.
     */
    public void startTrial();
                                
    /**
     * Method that blocks until trial is ongoing.
     */
    public void waitForTrialConclusion(); 
    
    /**
     * Method used to evaluate who won the trial.
     */
    public void assertTrialDecision();
    
    /**
     * Method used to check if a knockout occured during the trial.
     * 
     * @return String - Representation of knockout or not and for what team
     */
    public String checkKnockout();

    /**
     * Method used to return the current position of the rope
     * @return int - Position of the rope
     */
    public int getRope();
    
    /**
     * Method used to get the number of trial wins if each team.
     * @return int[] - Number of trial wins if each team.
     */
    public int[] getWins();

    /**
     * Method used to get the number of game wins if each team.
     * @return int[] - Number of wins if each team.
     */
    public int[] getGameWins();
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}


