/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 *
 * @author brunosilva
 */
public interface IBenchCoach {
    public void reviewNotes(String team);  //Esperar que os jogadores se sentem                      
    
    public void callContestants(String team); //Chamar n jogadores aleatoriamente para a trial e chamar informReferee
    
    public void waitForRefCommand(); //Esperar que o árbitro faça callTrial
    
    public boolean hasMatchFinished();
    
    public void playersReady(String team);
}
