/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.Bench;

/**
 * Protocol to handle messages for the Bench
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class BenchProtocol {
    private final Bench bench;
    
    /**
     * Constructor for the protocol.
     * @param bench Bench - Bench to be used by the protocol.
     */
    public BenchProtocol(Bench bench){
        this.bench = bench;
    }
    
    /**
     * Method to process the messages.
     * 
     * @param input - Message string
     * @return Null or methods return.
     * @throws UnsupportedOperationException - Exception for unsupported operation.
     * @throws EndOfTransactionException - Exception for End Of Transaction.
     */
    public Object processInput(String input) throws UnsupportedOperationException, EndOfTransactionException{
        if(input == null){
            throw new EndOfTransactionException("Close");
        }
        String[] methodCall = input.split("-");
        String method = methodCall[0];
        String[] args;
        switch (method) {
            case "reviewNotes":
                args = methodCall[1].split(";");
                bench.reviewNotes(args[0]);
                return null;
            case "callContestants":
                args = methodCall[1].split(";");
                return bench.callContestants(args[0]);
            case "followCoachAdvice":
                args = methodCall[1].split(";");
                bench.followCoachAdvice(args[0]);
                return null;
            case "waitForRefCommand":
                bench.waitForRefCommand();
                return null;
            case "signalCoaches":
                bench.signalCoaches();
                return null;
            case "seatAtTheBench":
                args = methodCall[1].split(";");
                return bench.seatAtTheBench(args[0], Integer.parseInt(args[1]));
            case "hasMatchFinished":
                return bench.hasMatchFinished();
            case "setMatchFinish":
                bench.setMatchFinish();
                return null;
            case "seatDown":
                args = methodCall[1].split(";");
                bench.seatDown(args[0]);
                return null;
            case "playersReady":
                args = methodCall[1].split(";");
                bench.playersReady(args[0]);
                return null;
            case "waitForPlayers":
                bench.waitForPlayers();
                return null;
            case "waitForCoaches":
                bench.waitForCoaches();
                return null;
            case "close":
                throw new EndOfTransactionException("Close");
            default:
                throw new UnsupportedOperationException();
        }        
    }
}
