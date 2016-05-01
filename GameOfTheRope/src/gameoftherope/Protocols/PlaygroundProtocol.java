/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.Playground;

/**
 * Protocol to handle messages for the Playground
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlaygroundProtocol {
    private final Playground playground;
    
    /**
     * Constructor for the protocol.
     * @param playground Playground - Playground to be used by the protocol.
     */
    public PlaygroundProtocol(Playground playground){
        this.playground = playground;
    }
    
    /**
     * Method to process messages.
     * @param input String - The message received.
     * @return The return given by the playground.
     * @throws UnsupportedOperationException
     * @throws EndOfTransactionException
     */
    public Object processInput(String input) throws UnsupportedOperationException, EndOfTransactionException{
        if(input == null){
            throw new EndOfTransactionException("Close");
        }
        
        String[] methodCall = input.split("-");
        String method = methodCall[0];
        switch (method) {
            case "waitForTrial":
                playground.waitForTrial();
                break;
            case "standInPosition":
                return playground.standInPosition();
            case "pullTheRope":
                //Falta robustes
                String[] args = methodCall[1].split(";");
                int strenght;
                String team;
                strenght = Integer.parseInt(args[0]);
                team = args[1];
                playground.pullTheRope(strenght, team);
                break;
            case "iamDone":
                playground.iamDone();
                break;
            case "callTrial":
                playground.callTrial();
                break;
            case "startTrial":
                playground.startTrial();
                break;
            case "waitForTrialConclusion":
                playground.waitForTrialConclusion();
                break; 
            case "assertTrialDecision":
                playground.assertTrialDecision();
                break; 
            case "checkKnockout":
                return playground.checkKnockout();
            case "getRope":
                return playground.getRope();
            case "getWins":
                return playground.getWins();
            case "getGameWins":
                return playground.getGameWins();
            case "close":
                throw new EndOfTransactionException("Close");
            default:
                throw new UnsupportedOperationException();
        }
        
        return null;
    }
}
