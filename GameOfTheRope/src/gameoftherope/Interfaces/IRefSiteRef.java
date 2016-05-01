/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Referee interaction with the RefSite.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IRefSiteRef {
    
    /**
     * Method to wait for coach signal.
     */
    public void waitForCoach(); 
    
    /**
     * Method to announce new game.
     */
    public void announceNewGame(); 
    
    /**
     * Method to declare the game winner.
     */
    public void declareGameWinner(); 
    
    /**
     * Method to declare the match winner.
     */
    public void declareMatchWinner(); 
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}
