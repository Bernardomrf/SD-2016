/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;

/**
 *
 * @author brunosilva
 */
public class Player extends Thread{
    private enum State { 
        SEAT_AT_THE_BENCH, STAND_IN_POSITION, DO_YOUR_BEST
    }
    private static final int maxStrength = 4;
    
    private final IBenchPlayer bench;
    private final IPlaygroundPlayer playground;
    private boolean goOn = true;
    private State internalState;
    private final String team;
    private final int strength;
    private final int id;
        
    public Player(IPlaygroundPlayer playground, IBenchPlayer bench, String team, int id){
        this.team = team;
        this.bench = bench;
        this.playground = playground;
        this.internalState = State.SEAT_AT_THE_BENCH;
        this.strength = (int) (Math.random() * maxStrength + 1);
        this.id = id;
    }
    
    @Override
    public void run(){
        while(goOn){
            switch(internalState){
                case SEAT_AT_THE_BENCH:
                    bench.seatDown(team);
                    bench.seatAtTheBench(team, id); // bloqueante - espera pelo coach
                    if(bench.hasMatchFinished()){
                        goOn = false;
                        break;
                    }
                    bench.followCoachAdvice(team); // sai do banco(variaveis!!!!)
                    internalState= State.STAND_IN_POSITION;
                    break;
                case STAND_IN_POSITION:
                    playground.standInPosition(); // bloqueante - espera pelo arbitro
                    internalState= State.DO_YOUR_BEST;
                    break;
                case DO_YOUR_BEST:
                    playground.pullTheRope(strength, team); // puxa a corda(variaveis!!!!)
                    playground.iamDone(); //o sexto jogador a chamar faz notify ao arbitro
                    
                    internalState= State.SEAT_AT_THE_BENCH;
                    break;
            }
        }
    }
}
