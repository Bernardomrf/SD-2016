/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import EntitiesProxy.ConfigProxy;
import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.Regions.Configs.GeneralRepositoryConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author brunosilva
 */
public class GeneralRepository {
    
    private int nTeamPlayers;
    private int nTrialPlayers;
    private int nCoaches;
    
    private final int [] playersStrengthA;
    private final int [] playersStrengthB;
    private final playerState [] playersStatesA;
    private final playerState [] playersStatesB;
    private final coachState [] coachesStates;
    private refState refereeState;
    private final int [] trialPosA;
    private final int [] trialPosB;
    private int trialN;
    private int ropePos;
    private int gamesA;
    private int gamesB;
    private int trialsA;
    private int trialsB;
    private int inPositionA;
    private int inPositionB;
    private String knockout;
    
    
    private final File log;
    private final String filename;
    private static PrintWriter pw;
    
    /**
     *
     * @throws FileNotFoundException
     */
    public GeneralRepository() throws FileNotFoundException{
        config();
        playersStrengthA = new int[nTeamPlayers];
        playersStrengthB = new int[nTeamPlayers];
        playersStatesA = new playerState[nTeamPlayers];
        playersStatesB = new playerState[nTeamPlayers];
        coachesStates = new coachState[nCoaches];
        refereeState = null;
        trialPosA = new int[nTrialPlayers];
        trialPosB = new int[nTrialPlayers];
        trialN = 0;
        ropePos = 0;
        gamesA = 0;
        gamesB = 0;
        trialsA = 0;
        trialsB = 0;
        inPositionA = 0;
        inPositionB = 0;
        knockout = "newGame";
        
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
        this.filename = "GameOfTheRope_" + date.format(today) + ".log";
        
        this.log = new File(this.filename);
        pw = new PrintWriter(log);
        pw.println("                               Game of the Rope - Description of the internal state");
        pw.println();
    }
    
    /**
     *
     */
    public synchronized void printHeader(){
            pw.println("Ref Coa 1 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5 Coa 2 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5       Trial");
            pw.println("Sta  Stat Sta SG Sta SG Sta SG Sta SG Sta SG  Stat Sta SG Sta SG Sta SG Sta SG Sta SG 3 2 1 . 1 2 3 NB PS");
    }
    
    /**
     *
     * @param state
     */
    public synchronized void changeRefState(refState state){
        refereeState = state;
        printLine();
    }
    
    /**
     *
     * @param state
     * @param id
     * @param team
     * @param strength
     */
    public synchronized void changePlayerState(playerState state, int id, String team, int strength){
        if (team.equals("A")){
            if (state == playersStatesA[id]) {
                return;
            }
            playersStrengthA[id] = strength;
            playersStatesA[id] = state;
        }
        else if (team.equals("B")){
            if (state == playersStatesB[id]) {
                return;
            }
            playersStrengthB[id] = strength;
            playersStatesB[id] = state;
        }
        
        printLine();
    }
    
    /**
     *
     * @param state
     * @param team
     */
    public synchronized void changeCoachState(coachState state, String team){
        if (team.equals("A")){
            if (state == coachesStates[0]) {
                return;
            }
            coachesStates[0] = state;
        }
        else if (team.equals("B")){
            if (state == coachesStates[1]) {
                return;
            }
            coachesStates[1] = state;
        }
        
        printLine();
    }
    
    /**
     *
     * @param state
     * @param strength
     * @param id
     * @param team
     */
    public synchronized void initPlayer(playerState state, int strength, int id, String team){
        if (team.equals("A")){
            playersStatesA[id] = state;
            playersStrengthA[id] = strength;
        }
        else if (team.equals("B")){
            playersStatesB[id] = state;
            playersStrengthB[id] = strength;
        }
    }
    
    /**
     *
     * @param state
     * @param team
     */
    public synchronized void initCoach(coachState state, String team){
        if (team.equals("A")){
            coachesStates[0] = state;
        }
        else if (team.equals("B")){
            coachesStates[1] = state;
        }        
    }
    
    /**
     *
     * @param state
     */
    public synchronized void initRef(refState state){
        refereeState = state;
    }
    
    /**
     *
     */
    public void printLine(){
        pw.print(refereeState);
        pw.print("  ");
        pw.print(coachesStates[0]);
        pw.print(" ");
        for(int i = 0; i < playersStatesA.length; i++){
            pw.print(playersStatesA[i]);
            pw.print("  ");
            pw.print(playersStrengthA[i]);
            pw.print(" ");
        }
        pw.print(" ");
        pw.print(coachesStates[1]);
        pw.print(" ");
        for(int i = 0; i < playersStatesB.length; i++){
            pw.print(playersStatesB[i]);
            pw.print("  ");
            pw.print(playersStrengthB[i]);
            pw.print(" ");
        }
        for(int i = 0; i<playersStatesA.length;i++){
            
            if(playersStatesA[i].equals(playerState.STAND_IN_POSITION) || playersStatesA[i].equals(playerState.DO_YOUR_BEST)){
                inPositionA++;
                pw.print((i+1) + " ");
            }
        }
        if(inPositionA != 3){
            for (int i = inPositionA; i<3;i++){
                pw.print("- ");
            }
        }
        pw.print(". ");

        for(int i = 0; i<playersStatesB.length;i++){
            
            if(playersStatesB[i].equals(playerState.STAND_IN_POSITION) || playersStatesB[i].equals(playerState.DO_YOUR_BEST)){
                inPositionB++;
                pw.print((i+1) + " ");
            }
        }
        if(inPositionB != 3){
            for (int i = inPositionB; i<3;i++){
                pw.print("- ");
            }
        }
        inPositionA = 0;
        inPositionB = 0;
        pw.print(" " + trialN);
        pw.print(" " + ropePos);
        pw.println();
        pw.flush();

    }
    
    /**
     *
     * @param pos
     * @param team
     */
    public synchronized void setPlayersPositions(int[] pos, String team){
        if (team.equals("A")){
            System.arraycopy(pos, 0, trialPosA, 0, trialPosA.length);  
        }
        else if (team.equals("B")){
            System.arraycopy(pos, 0, trialPosB, 0, trialPosB.length);
        }
    }
    private boolean contains(int [] array, int value){
        for(int i = 0; i < array.length; i++){
            if (array[i] == value)
                return true;
        }
        return false;
    }
    
    /**
     *
     * @param nGame
     */
    public synchronized void newGame(int nGame){
        if(knockout.equals("newGame")){
            pw.println("Game "+ (nGame+1));
            pw.flush();
            printHeader();
            return;
        }
        if(!knockout.equals("X")){
            pw.println("Game "+(nGame)+" was won by team "+knockout+" by knock out in "+ (trialsA+trialsB) +" trials.");
        }
        if(knockout.equals("X")){
            if(trialsA == trialsB){
                pw.println("Game "+(nGame)+" was a draw.");
            }
            else if(trialsA>trialsB){
                pw.println("Game "+(nGame)+" was won by team A by points.");
            }
            else if(trialsA<trialsB){
                pw.println("Game "+(nGame)+" was won by team B by points.");
            }
        }
        pw.println("Game "+ (nGame+1));
        pw.flush();
        printHeader();
    }
    
    /**
     *
     * @param nTrial
     */
    public synchronized void newTrial(int nTrial){
        trialN = nTrial;
    }
    
    /**
     *
     * @param rope
     */
    public synchronized void setRope(int rope){
        ropePos = rope;
    }

    /**
     *
     * @param wins
     * @param knockout
     */
    public synchronized void setWins(int[] wins, String knockout){
        trialsA = wins[0];
        trialsB = wins[1];
        this.knockout = knockout;
    }

    /**
     *
     * @param gameWins
     * @param nGame
     */
    public synchronized void setGameWins(int[] gameWins, int nGame){
        gamesA = gameWins[0];
        gamesB = gameWins[1];
        finishMatch(nGame);
    }
    
    /**
     *
     * @param nGame
     */
    public synchronized void finishMatch(int nGame){
       
        if(!knockout.equals("X")){
            pw.println("Game "+(nGame)+" was won by team "+knockout+" by knock out in "+ (trialsA+trialsB) +" trials.");
        }
        if(knockout.equals("X")){
            if(trialsA == trialsB){
                pw.println("Game "+(nGame)+" was a draw.");
            }
            else if(trialsA>trialsB){
                pw.println("Game "+(nGame)+" was won by team A by points.");
            }
            else if(trialsA<trialsB){
                pw.println("Game "+(nGame)+" was won by team B by points.");
            }
        }
        if(gamesA == gamesB){
            pw.println("Match was a draw.");
        }
        else if(gamesA > gamesB){
            pw.println("Match was won by team A ("+gamesA+"-"+gamesB+").");
        }
        else if(gamesA < gamesB){
            pw.println("Match was won by team B ("+gamesA+"-"+gamesB+").");
        }
        pw.flush();
        
    }

    private void config() {
        ConfigProxy conf = new ConfigProxy();
        GeneralRepositoryConfig settings = conf.getGeneralRepositoryConfig();
        
        nCoaches = settings.getnCoaches();
        nTeamPlayers = settings.getnTeamPlayers();
        nTrialPlayers = settings.getNtrialPlayers();
    }
}
