/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ClientSide.Referee;

import gameoftherope.Configs.RefConfig;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.Interfaces.IBenchRef;
import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Interfaces.IGeneralRepositoryRef;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Interfaces.IRefSiteRef;
import gameoftherope.VectorClock.VectorClock;
import java.rmi.RemoteException;


/**
 * Class for the Referee entity.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class Referee extends Thread{

    
    private final IRefSiteRef refSite;
    private final IPlaygroundRef playground;
    private final IBenchRef bench;
    private final IConfigRepository conf;
    private final IGeneralRepositoryRef repo;

    private boolean goOn = true;
    private refState internalState;
    private int trialsDone;
    private int gamesDone;
    private int nTrials;
    private int nGames;
    private String knockOut;
    private int rope;
    private int[] wins;
    private int[] gameWins;
    private Object[] returns;
    
    private final VectorClock ownClock;
    private VectorClock returnClock;
    
    /**
     * Constructor for Referee class.
     * 
     * @param refSite IRefSiteRef - Interface for the Referee Ref Site.
     * @param playground IPlaygroundRef - Interface for the Referee Playground.
     * @param bench IBenchRef - Interface for the Referee Bench.
     * @param repo IGeneralRepositoryRef - Interface for the Referee General Repository.
     * @param conf IConfigRepository - Interface for the Referee Config Repository.
     * @throws java.rmi.RemoteException
     */
    public Referee(IRefSiteRef refSite, IPlaygroundRef playground, IBenchRef bench, IGeneralRepositoryRef repo, IConfigRepository conf) throws RemoteException{
	this.conf = conf;
        config();
        this.refSite = refSite;
        this.playground = playground;
        this.bench = bench;
        this.repo = repo;
        this.internalState = refState.START_OF_THE_MATCH;
        this.trialsDone = 0;
        this.gamesDone = 0;
        this.knockOut = "X";
        this.rope = 0;
        this.wins = new int[2];
        this.gameWins = new int[2];
        
        ownClock = new VectorClock(13,0);
        
        ownClock.increment();
        returnClock = repo.printHeader(ownClock.clone());
        ownClock.update(returnClock);
        
        ownClock.increment();
        returnClock = repo.initRef(internalState, ownClock.clone());
        ownClock.update(returnClock);
    }
    
    @Override
    public void run(){
        try {
            bench.waitForPlayers();
            bench.waitForCoaches();
            while(goOn){
                switch(internalState){
                    case START_OF_THE_MATCH:
                        ownClock.increment();
                        returnClock = repo.changeRefState(internalState, ownClock.clone());
                        ownClock.update(returnClock);
                        //System.out.println(internalState);
                        
                        ownClock.increment();
                        returnClock = refSite.announceNewGame(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        internalState= refState.START_OF_A_GAME;
                        
                        ownClock.increment();
                        returnClock = repo.newGame(gamesDone, ownClock.clone());
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = repo.changeRefState(internalState, ownClock.clone());
                        ownClock.update(returnClock);
                        break;
                    case START_OF_A_GAME: 
                        ownClock.increment();
                        returnClock = playground.callTrial(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = bench.signalCoaches(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        internalState= refState.TEAMS_READY;
                        
                        ownClock.increment();
                        returnClock = repo.newTrial(trialsDone+1, ownClock.clone());
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = repo.changeRefState(internalState, ownClock.clone());
                        ownClock.update(returnClock);
                        break;
                    case TEAMS_READY:
                        ownClock.increment();
                        returnClock = refSite.waitForCoach(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        ownClock.increment();
                        returnClock = playground.startTrial(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        internalState= refState.WAIT_FOR_TRIAL_CONCLUSION;
                        
                        FALTA DAQUI PARA BAIXO!!!
                        
                        
                        repo.changeRefState(internalState, ownClock.clone());
                        break;
                    case WAIT_FOR_TRIAL_CONCLUSION:
                        ownClock.increment();
                        returnClock = playground.waitForTrialConclusion(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        //System.out.println("Novo Trial");
                        ownClock.increment();
                        returnClock = playground.assertTrialDecision(ownClock.clone());
                        ownClock.update(returnClock);
                        
                        trialsDone ++;
                        wins = playground.getWins();
                        
                        ownClock.increment();
                        returns = playground.checkKnockout(ownClock.clone());
                        ownClock.update((VectorClock)returns[1]);
                        knockOut = (String)returns[0];
                        //System.err.println("trial Done");
                        if (trialsDone == nTrials || !knockOut.equals("X")){
                            //System.out.println("Knockout " + knockOut);
                            internalState= refState.END_OF_A_GAME;
                            rope = playground.getRope();
                            repo.setRope(rope, ownClock.clone());
                            repo.changeRefState(internalState, ownClock.clone());
                        }
                        else{
                            rope = playground.getRope();
                            repo.setRope(rope, ownClock.clone());
                            internalState= refState.START_OF_A_GAME;
                            repo.changeRefState(internalState, ownClock.clone());
                        }
                        break;
                    case END_OF_A_GAME:
                        trialsDone = 0;
                        refSite.declareGameWinner();
                        //System.out.println(gamesDone);
                        gamesDone ++;
                        if (gamesDone == nGames){
                            internalState= refState.END_OF_THE_MATCH;
                            repo.setWins(wins, knockOut, ownClock.clone());
                            repo.changeRefState(internalState, ownClock.clone());
                        }
                        else{
                            rope = playground.getRope();
                            repo.setRope(rope, ownClock.clone());
                            internalState= refState.START_OF_A_GAME;
                            repo.setWins(wins, knockOut, ownClock.clone());
                            repo.newGame(gamesDone, ownClock.clone());
                            repo.newTrial(trialsDone, ownClock.clone());
                            repo.changeRefState(internalState, ownClock.clone());
                        }
                        break;
                    case END_OF_THE_MATCH:
                        refSite.declareMatchWinner();
                        ownClock.increment();
                        returnClock = bench.setMatchFinish(ownClock.clone()); // Fazer setMatchFinish no playground e no refSite
                        ownClock.update(returnClock);
                        
                        gameWins = playground.getGameWins();
                        repo.setGameWins(gameWins, gamesDone, ownClock.clone());
                        goOn = false;
                        break;    

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    private void config() throws RemoteException{
        RefConfig settings = conf.getRefConfig();
        nGames = settings.getNGames();
        nTrials = settings.getNTrials();
 
    }
}
