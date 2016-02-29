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
public interface IPlaygroundRef {
    public void callTrial();
    public void waitForCoach(); // Esperar pelo inform referee dos dois treinadores
    
    public void startTrial();
    public void assertTrialDecision();
}
