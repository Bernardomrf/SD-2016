/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.Regions.Playground;

/**
 *
 * @author bernardo
 */
public class PlaygroundProtocol {
    private final Playground playground;
    
    public PlaygroundProtocol(Playground playground){
        this.playground = playground;
    }
    
    public Object processInput(String input) throws UnsupportedOperationException{
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
            
            default:
                throw new UnsupportedOperationException();
        }
        
        return null;
    }
}
