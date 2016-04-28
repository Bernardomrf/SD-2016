/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Configs.PlayerConfig;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;

/**
 * Class for the Player entity.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class Player extends Thread{
    private int maxStrength;
    
    private final IBenchPlayer bench;
    private final IPlaygroundPlayer playground;
    private final IConfigRepository conf;
    private final IGeneralRepositoryPlayer repo;
    private boolean goOn = true;
    private playerState internalState;
    private final String team;
    private int strength;
    private final int id;
    private boolean iWillPlay;
    private int nTrials;
    private int nPlayerTrials;
    
    /**
     * Constructor for PLayer class
     *
     * @param playground IPlaygroundPlayer - Interface for the player Playground.
     * @param bench IBenchPlayer - Interface for the player Bench.
     * @param team String - Team of the player.
     * @param id int - Id of the players.
     * @param repo IGeneralRepositoryPlayer - Interface for the player General Repository.
     * @param conf IConfigRepository - Interface for the player Config Repository.
     */
    public Player(IPlaygroundPlayer playground, IBenchPlayer bench, String team, int id, IGeneralRepositoryPlayer repo, IConfigRepository conf){
		this.conf = conf;
        config();
        this.team = team;
        this.bench = bench;
        this.playground = playground;
        this.repo = repo;
        this.internalState = playerState.SEAT_AT_THE_BENCH;
        this.strength = (int) (Math.random() * maxStrength + 1);
        this.id = id;
        this.iWillPlay = false;
        this.nPlayerTrials = 0;
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
                        /*if(strength < 5){
                            strength++;
                        }*/
                        break;
                    }
                    bench.followCoachAdvice(team);
                    // sai do banco(variaveis!!!!)
                    internalState= playerState.STAND_IN_POSITION;
                    repo.changePlayerState(internalState, id, team, strength);
                    break;
                case STAND_IN_POSITION:
                    nTrials = playground.standInPosition();
                    if(nPlayerTrials<nTrials){
                        strength+=nTrials-nPlayerTrials-1;
                        if(strength>maxStrength){
                            strength = maxStrength;
                        }
                        nPlayerTrials = nTrials;
                    }
                    // bloqueante - espera pelo arbitro
                    internalState= playerState.DO_YOUR_BEST;
                    repo.changePlayerState(internalState, id, team, strength);
                    break;
                case DO_YOUR_BEST:
                    playground.pullTheRope(strength, team); // puxa a corda(variaveis!!!!)
                    repo.changePlayerState(internalState, id, team, strength);
                    playground.iamDone(); //o sexto jogador a chamar faz notify ao arbitro
                    if(strength > 0){
                        strength--;
                    }
                    internalState= playerState.SEAT_AT_THE_BENCH;
                    repo.changePlayerState(internalState, id, team, strength);
                    break;
            }
        }
        
        bench.close();
        playground.close();
        repo.close();
        conf.close();
    }
    
    private void config(){
        PlayerConfig settings = conf.getPlayerConfig();
        maxStrength = settings.getMaxStrength();
    }
}
