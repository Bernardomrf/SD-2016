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

    /**
     *
     * @param team
     * @param id
     * @return
     */
    public boolean seatAtTheBench(String team, int id); // Fica bloqueado no banco
    
    /**
     *
     * @param team
     */
    public void seatDown(String team);
    
    /**
     *
     * @param team
     */
    public void followCoachAdvice(String team); // passa para o playgound; Jogadores no banco decrementa, jogadores para jogar aumenta
                                    // Variavel independente para cada equipa
    
    /**
     *
     * @return
     */
    public boolean hasMatchFinished();
    
    public void close();
}
