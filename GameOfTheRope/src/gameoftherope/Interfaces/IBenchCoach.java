/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Coach interaction with the Bench.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IBenchCoach {

    /**
     * Method blocks and waits for all players to be seated at the bench.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                     
     */
    public void reviewNotes(String team);  //Esperar que os jogadores se sentem                      
    
    /**
     * This method generates an array of random player numbers to be called to play.
     * Method does not block, and notifies players.
     * 
     * @param team String - A String representing what team the coach belongs to. 
     *                      
     * @return int[] - An array containing the players that will play.
     */
    public int [] callContestants(String team); //Chamar n jogadores aleatoriamente para a trial e chamar informReferee
    
    /**
     * Method blocks and waits for the referee to come to the bench.
     */
    public void waitForRefCommand(); //Esperar que o árbitro faça callTrial
    
    /**
     * Method to check if the match has finished.
     * 
     * @return boolean - true if the match has finished, false if not.
     */
    public boolean hasMatchFinished();
    
    /**
     * It's called by coaches only.
     *
     * @param team String - A String representing what team the coach belongs to. 
     *                      
     */
    public void playersReady(String team);
    
    /**
     * Method used to represent the end of use of the bench.
     */
    public void close();
    
}
