/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Coach interaction with the Playground.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IPlaygroundCoach {
    
    /**
     * Method used to wait for the trial to end.
     */
    public void waitForTrial(); // Esperar que o trial acabe 
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}
