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
public interface IBenchPlayer {
    public void seatAtTheBench(String team); // Fica bloqueado no banco
    
    public void seatDown(String team);
    
    public void followCoachAdvice(); // passa para o playgound; Jogadores no banco decrementa, jogadores para jogar aumenta
                                    // Variavel independente para cada equipa
    
    public boolean hasMatchFinished();
}
