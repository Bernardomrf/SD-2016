/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.Regions.RefSite;
/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteProtocol {
    private final RefSite refSite;
    
    public RefSiteProtocol(RefSite refSite){
        this.refSite = refSite;
    }
    
    public String processInput(String input) throws UnsupportedOperationException{
        
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
            default:
                throw new UnsupportedOperationException();
        }
        
        return null;
    }
}
