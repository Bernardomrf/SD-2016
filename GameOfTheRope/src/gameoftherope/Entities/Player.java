/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Regions.GeneralRepository;
import gameoftherope.playerState;

/**
 *
 * @author brunosilva
 */
public class Player extends Thread{

    
    private static final int maxStrength = 4;
    
    private final IBenchPlayer bench;
    private final IPlaygroundPlayer playground;
    private final GeneralRepository repo;
    private boolean goOn = true;
    private playerState internalState;
    private final String team;
    private int strength;
    private final int id;
    private boolean iWillPlay;
    
        
    public Player(IPlaygroundPlayer playground, IBenchPlayer bench, String team, int id, GeneralRepository repo){
        this.team = team;
        this.bench = bench;
        this.playground = playground;
        this.internalState = playerState.SEAT_AT_THE_BENCH;
        this.strength = (int) (Math.random() * maxStrength + 1);
        this.id = id;
        this.iWillPlay = false;
        this.repo = repo;
        repo.initPlayer(internalState, strength, id, team);
    }
    
    @Override
    public void run(){
        while(goOn){
            switch(internalState){
                case SEAT_AT_THE_BENCH:
                    
                    bench.seatDown(team);
                    iWillPlay = bench.seatAtTheBench(team, id); // bloqueante - espera pelo coach
                    if(bench.hasMatchFinished()){
                        goOn = false;
                        break;
                    }
                    if (!iWillPlay){
                        if(strength < 5){
                            strength++;
                        }
                        break;
                    }
                    bench.followCoachAdvice(team); // sai do banco(variaveis!!!!)
                    internalState= playerState.STAND_IN_POSITION;
                    break;
                case STAND_IN_POSITION:
                    playground.standInPosition(); // bloqueante - espera pelo arbitro
                    internalState= playerState.DO_YOUR_BEST;
                    break;
                case DO_YOUR_BEST:
                    playground.pullTheRope(strength, team); // puxa a corda(variaveis!!!!)
                    playground.iamDone(); //o sexto jogador a chamar faz notify ao arbitro
                    if(strength > 0){
                        strength--;
                    }
                    internalState= playerState.SEAT_AT_THE_BENCH;
                    break;
            }
        }
    }
}
