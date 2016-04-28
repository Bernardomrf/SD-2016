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

    /**
     *
     */
    public void callTrial();//++ // Acorda os treinadores do waitForRefCommand
    
    /**
     *
     */
    public void startTrial(); //+ Dá inicio a trial
                                
    /**
     *
     */
    public void waitForTrialConclusion(); //++ bloqueia a espera que o ultimo jogador acabe (imDone)
    
    /**
     *
     */
    public void assertTrialDecision(); //++ Ve se todos os jogadores ja jogaram (playersDone) se não volta a bloquear em waitForTrialConclusion
                                        // Se todos os jogadores já tiverem jogado da uma decisao do trial
                                        // Se for o ultimo trial mudar o estado para refSite
                                        // Se nao for o ultmo trial acorda treinador do watchTrial e voltar a chamar callTrial    
    
    /**
     *
     * @return
     */
    public String checkKnockout(); //++

    /**
     *
     * @return
     */
    public int getRope(); //++
    
    /**
     *
     * @return
     */
    public int[] getWins(); //++

    /**
     *
     * @return
     */
    public int[] getGameWins(); //++
    
    /**
     *
     */
    public void close();
}


