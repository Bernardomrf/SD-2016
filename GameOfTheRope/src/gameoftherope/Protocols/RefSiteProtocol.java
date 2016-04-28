/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.RefSite;
/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteProtocol {
    private final RefSite refSite;
    
    /**
     *
     * @param refSite
     */
    public RefSiteProtocol(RefSite refSite){
        this.refSite = refSite;
    }
    
    /**
     *
     * @param input
     * @return
     * @throws UnsupportedOperationException
     * @throws EndOfTransactionException
     */
    public String processInput(String input) throws UnsupportedOperationException, EndOfTransactionException{
        if(input == null){
            throw new EndOfTransactionException("Close");
        }
        switch (input) {
            case "informReferee":
                refSite.informReferee();
                break;
            case "waitForCoach":
                refSite.waitForCoach();
                break;
            case "announceNewGame":
                refSite.announceNewGame();
                break;
            case "declareGameWinner":
                refSite.declareGameWinner();
                break;
            case "declareMatchWinner":
                refSite.declareMatchWinner();
                break;
            case "close":
                throw new EndOfTransactionException("Close");
            default:
                throw new UnsupportedOperationException();
        }
        
        return null;
    }
}
