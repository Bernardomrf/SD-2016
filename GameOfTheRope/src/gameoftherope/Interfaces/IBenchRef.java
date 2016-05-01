/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Referee interaction with the Bench.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IBenchRef {

    /** 
     * Method does not block and notifies the coaches.
     */
    public void signalCoaches(); //acorda os treinadores
    
    /**
     * Method to set the match has finished.
     */
    public void setMatchFinish();
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
    
    /**
     * Method called by the referee to wait for all players to be ready before 
     * starting the match.
     */
    public void waitForPlayers();
    
    /**
     * Method called by the referee to wait for all coaches to be ready before 
     * starting the match.
     */
    public void waitForCoaches();
}
