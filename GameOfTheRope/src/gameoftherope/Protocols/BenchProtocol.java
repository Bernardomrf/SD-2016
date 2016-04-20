/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.Regions.Bench;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class BenchProtocol {
    private final Bench bench;
    
    public BenchProtocol(Bench bench){
        this.bench = bench;
    }
    
    public Object processInput(String input) throws UnsupportedOperationException{
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
            default:
                throw new UnsupportedOperationException();
        }        
    }
}
