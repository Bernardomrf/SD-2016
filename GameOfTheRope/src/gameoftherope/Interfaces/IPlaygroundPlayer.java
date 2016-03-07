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
public interface IPlaygroundPlayer {
    public void standInPosition(); // Esperar pelo startTrial que espera pelo coach
    
    public void pullTheRope(); // altera uma variavel partilhada incrementando/decrementando dependendo da equipa
    
    public void iamDone(); // incrementa uma variavel de jogadores que já acabaram, ultimo faz notify ao arbitro
                            // Muda o estado para o bench
}
