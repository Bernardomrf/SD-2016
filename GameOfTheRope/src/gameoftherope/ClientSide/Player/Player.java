/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Player;

import gameoftherope.Configs.PlayerConfig;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.VectorClock.VectorClock;
import java.rmi.RemoteException;

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
    
    private Object[] returns;
    private final VectorClock ownClock;
    private VectorClock returnClock;
    
    /**
     * Constructor for PLayer class
     *
     * @param playground IPlaygroundPlayer - Interface for the player Playground.
     * @param bench IBenchPlayer - Interface for the player Bench.
     * @param team String - Team of the player.
     * @param id int - Id of the players.
     * @param repo IGeneralRepositoryPlayer - Interface for the player General Repository.
     * @param conf IConfigRepository - Interface for the player Config Repository.
     * @throws java.rmi.RemoteException
     */
    public Player(IPlaygroundPlayer playground, IBenchPlayer bench, String team, int id, IGeneralRepositoryPlayer repo, IConfigRepository conf) throws RemoteException{
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
        
        if (team.equals("A")){
            ownClock = new VectorClock(13,3+id);
        }else{
            ownClock = new VectorClock(13,8+id);
        }
        
        repo.initPlayer(internalState, strength, id, team, ownClock.clone());
    }
    
    @Override
    public void run(){
        try {
            while(goOn){
                switch(internalState){
                    case SEAT_AT_THE_BENCH:
                        
                        ownClock.increment();
                        returnClock = bench.seatDown(team, ownClock.clone());
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returns = bench.seatAtTheBench(team, id, ownClock.clone()); // bloqueante - espera pelo coach
                        ownClock.update((VectorClock)returns[1]);
                        iWillPlay = (boolean)returns[0];
                        
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
                        ownClock.increment();
                        returnClock = bench.followCoachAdvice(team, ownClock.clone());
                        ownClock.update(returnClock);
                        
                        // sai do banco(variaveis!!!!)
                        internalState= playerState.STAND_IN_POSITION;
                        repo.changePlayerState(internalState, id, team, strength, ownClock.clone());
                        break;
                    case STAND_IN_POSITION:
                        ownClock.increment();
                        returns = playground.standInPosition(ownClock.clone());
                        ownClock.update((VectorClock)returns[1]);
                        nTrials = (int)returns[0];
                        
                        if(nPlayerTrials<nTrials){
                            strength+=nTrials-nPlayerTrials-1;
                            if(strength>maxStrength){
                                strength = maxStrength;
                            }
                            nPlayerTrials = nTrials;
                        }
                        // bloqueante - espera pelo arbitro
                        internalState= playerState.DO_YOUR_BEST;
                        repo.changePlayerState(internalState, id, team, strength, ownClock.clone());
                        break;
                    case DO_YOUR_BEST:
                        ownClock.increment();
                        returnClock = playground.pullTheRope(strength, team, ownClock.clone()); // puxa a corda(variaveis!!!!)
                        ownClock.update(returnClock);
                        
                        repo.changePlayerState(internalState, id, team, strength, ownClock.clone());
                        
                        ownClock.increment();
                        returnClock = playground.iamDone(ownClock.clone()); //o sexto jogador a chamar faz notify ao arbitro
                        ownClock.update(returnClock);
                        
                        if(strength > 0){
                            strength--;
                        }
                        
                        internalState= playerState.SEAT_AT_THE_BENCH;
                        repo.changePlayerState(internalState, id, team, strength, ownClock.clone());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void config() throws RemoteException{
        PlayerConfig settings = conf.getPlayerConfig();
        maxStrength = settings.getMaxStrength();
    }
}
