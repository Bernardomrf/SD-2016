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
    public void reviewNotes();  //Esperar que os jogadores se sentem                      
    
    public void callContestants(); //Chamar n jogadores aleatoriamente para a trial e chamar informReferee
    
    public void waitForRefCommand(); //Esperar que o árbitro faça callTrial
}
