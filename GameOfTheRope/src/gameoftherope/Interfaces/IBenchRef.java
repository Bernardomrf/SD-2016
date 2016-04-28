/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IBenchRef {

    /**
     *
     */
    public void signalCoaches(); //acorda os treinadores
    
    /**
     *
     */
    public void setMatchFinish();
    
    /**
     *
     */
    public void close();
    
    /**
     *
     */
    public void waitForPlayers();
    
    /**
     *
     */
    public void waitForCoaches();
}
