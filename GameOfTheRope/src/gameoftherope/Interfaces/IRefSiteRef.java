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
public interface IRefSiteRef {
    
    /**
     *
     */
    public void waitForCoach(); // (TeamsReady)  Esperar pelo informReferee dos dois treinadores
    
    /**
     *
     */
    public void announceNewGame(); // espera tempo aleatorio para começar um jogo novo
                                    // Muda o estado para o playground
    
    /**
     *
     * @param knockOut
     */
    public void declareGameWinner(); // espera que o estado mude para refSite
                                        // ver se é o ultimo jogo atraves da variavel aWins, bWins
                                        // Se for o ultimo jogo chamar declareMatchWinner
                                        // Se nao for o ultimo jogo chamar announceNewGame
    
    /**
     *
     */
    public void declareMatchWinner(); // Ver o vencedor, matar tudo !!!!
}
