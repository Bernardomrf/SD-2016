/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.RefSite;
/**
 * Protocol to handle messages for the Referee Site.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class RefSiteProtocol {
    private final RefSite refSite;
    
    /**
     * Constructor for the protocol.
     * @param refSite RefSite - Referee Site to be used by the protocol.
     */
    public RefSiteProtocol(RefSite refSite){
        this.refSite = refSite;
    }
    
    /**
     * Method to process messages.
     * @param input String - The message received.
     * @return The return given by the refSite.
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
