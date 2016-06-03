/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.GeneralRepository;

import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.Interfaces.IGeneralRepository;
import gameoftherope.Interfaces.IGeneralRepositoryCoach;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IGeneralRepositoryRef;
import gameoftherope.ServerSide.ConfigRepository.ConfigRepository;
import gameoftherope.VectorClock.VectorClock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * Class to implement the methods for the General Repository Region.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class GeneralRepository implements IGeneralRepositoryCoach, IGeneralRepositoryPlayer, IGeneralRepositoryRef, IGeneralRepository {
    
    
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
    
    
    private final File log, reorder_log;
    private final String filename, filename_reorder;
    private static PrintWriter pw, reorder;
    
    private final ArrayList<Update> updates;
    private int nEntitiesRunning;
    
    private final VectorClock clocks;
    
    /**
     * Constructor for the General Repository.
     * 
     */
    public GeneralRepository(){
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
        
        updates = new ArrayList<>();
        nEntitiesRunning = 2* nTeamPlayers + nCoaches + 1;
        
        clocks = new VectorClock(13, 0);
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
        this.filename = "GameOfTheRope_" + date.format(today) + ".log";
        this.filename_reorder = "GameOfTheRope_REORDERED_" + date.format(today) + ".log";
        
        this.log = new File(this.filename);
        this.reorder_log = new File(this.filename_reorder);
        try {
            pw = new PrintWriter(log);
            reorder = new PrintWriter(reorder_log);
        } catch (FileNotFoundException ex) {
        }
        String toWrite = "";
        toWrite += "                               Game of the Rope - Description of the internal state\n";
        
        pw.write(toWrite);
        pw.flush();
        Update u = new Update(toWrite, clocks.toIntArray());
        updates.add(u);
        
    }
    
    /**
     * Method used to print the log file header.
     */
    @Override
    public synchronized VectorClock printHeader(VectorClock vc){
        clocks.update(vc);
        String toWrite = "";
        toWrite += "Ref Coa 1 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5 Coa 2 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5       Trial                          VCk\n";
        toWrite += "Sta  Stat Sta SG Sta SG Sta SG Sta SG Sta SG  Stat Sta SG Sta SG Sta SG Sta SG Sta SG 3 2 1 . 1 2 3 NB PS 0  1  2  3  4  5  6  7  8  9 10 11 12\n";
        
        pw.write(toWrite);
        pw.flush();
        Update u = new Update(toWrite, clocks.toIntArray());
        updates.add(u);
        return clocks.clone();
    }
    
    /**
     * Method to change the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     */
    @Override
    public synchronized VectorClock changeRefState(refState state, VectorClock vc){
        clocks.update(vc);
        refereeState = state;
        printLine(clocks);
        return clocks.clone();
    }
    
    /**
     * Method to change the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Current strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     */
    @Override
    public synchronized VectorClock changePlayerState(playerState state, int id, String team, int strength, VectorClock vc){
        clocks.update(vc);
        if (team.equals("A")){
            if (state == playersStatesA[id]) {
                return clocks.clone();
            }
            playersStrengthA[id] = strength;
            playersStatesA[id] = state;
        }
        else if (team.equals("B")){
            if (state == playersStatesB[id]) {
                return clocks.clone();
            }
            playersStrengthB[id] = strength;
            playersStatesB[id] = state;
        }
        
        printLine(clocks);
        return clocks.clone();
    }
    
    /**
     * Method to change the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     */
    @Override
    public synchronized VectorClock changeCoachState(coachState state, String team, VectorClock vc){
        clocks.update(vc);
        if (team.equals("A")){
            if (state == coachesStates[0]) {
                return clocks.clone();
            }
            coachesStates[0] = state;
        }
        else if (team.equals("B")){
            if (state == coachesStates[1]) {
                return clocks.clone();
            }
            coachesStates[1] = state;
        }
        
        printLine(clocks);
        return clocks.clone();
    }
    
    /**
     * Method to initializate the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Initial strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     */
    @Override
    public synchronized VectorClock initPlayer(playerState state, int strength, int id, String team, VectorClock vc){
        clocks.update(vc);
        if (team.equals("A")){
            playersStatesA[id] = state;
            playersStrengthA[id] = strength;
        }
        else if (team.equals("B")){
            playersStatesB[id] = state;
            playersStrengthB[id] = strength;
        }
        return clocks.clone();
    }
    
    /**
     * Method to initializate the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     */
    @Override
    public synchronized VectorClock initCoach(coachState state, String team, VectorClock vc){
        clocks.update(vc);
        if (team.equals("A")){
            coachesStates[0] = state;
        }
        else if (team.equals("B")){
            coachesStates[1] = state;
        }
        return clocks.clone();
    }
    
    /**
     * Method to initializate the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     */
    @Override
    public synchronized VectorClock initRef(refState state, VectorClock vc){
        clocks.update(vc);
        refereeState = state;
        return clocks.clone();
    }
    
    /**
     * Method used to print a line in the log file.
     * @param vc
     */
    public void printLine(VectorClock vc){
        String toWrite = "";
        toWrite += refereeState;
        toWrite += "  ";
        toWrite += coachesStates[0];
        toWrite += " ";
        for(int i = 0; i < playersStatesA.length; i++){
            toWrite += playersStatesA[i];
            toWrite += "  ";
            toWrite += playersStrengthA[i];
            toWrite += " ";
        }
        toWrite += " ";
        toWrite += coachesStates[1];
        toWrite += " ";
        for(int i = 0; i < playersStatesB.length; i++){
            toWrite += playersStatesB[i];
            toWrite += "  ";
            toWrite += playersStrengthB[i];
            toWrite += " ";
        }
        for(int i = 0; i<playersStatesA.length;i++){
            
            if(playersStatesA[i].equals(playerState.STAND_IN_POSITION) || playersStatesA[i].equals(playerState.DO_YOUR_BEST)){
                inPositionA++;
                toWrite += (i+1) + " ";
            }
        }
        if(inPositionA != 3){
            for (int i = inPositionA; i<3;i++){
                toWrite += "- ";
            }
        }
        toWrite += ". ";

        for(int i = 0; i<playersStatesB.length;i++){
            
            if(playersStatesB[i].equals(playerState.STAND_IN_POSITION) || playersStatesB[i].equals(playerState.DO_YOUR_BEST)){
                inPositionB++;
                toWrite += (i+1) + " ";
            }
        }
        if(inPositionB != 3){
            for (int i = inPositionB; i<3;i++){
                toWrite += "- ";
            }
        }
        inPositionA = 0;
        inPositionB = 0;
        toWrite += " " + trialN;
        toWrite += " " + ropePos;
        
        int[] arrayClocks = vc.toIntArray();
        for (int i = 0; i < 13; i++) {
            toWrite += String.format(" %2d", arrayClocks[i]);
        }
        
        toWrite += "\n";
        
        pw.write(toWrite);
        pw.flush();
        Update u = new Update(toWrite, arrayClocks);
        updates.add(u);

    }
    
    /**
     * Method used to set the players positions for the trial.
     * @param pos int[] - Array containing the players positions.
     * @param team String - Team corresponding to the positions.
     */
    @Override
    public synchronized VectorClock setPlayersPositions(int[] pos, String team, VectorClock vc){
        clocks.update(vc);
        if (team.equals("A")){
            System.arraycopy(pos, 0, trialPosA, 0, trialPosA.length);  
        }
        else if (team.equals("B")){
            System.arraycopy(pos, 0, trialPosB, 0, trialPosB.length);
        }
        return clocks.clone();
    }
    
    private boolean contains(int [] array, int value){
        for(int i = 0; i < array.length; i++){
            if (array[i] == value)
                return true;
        }
        return false;
    }
    
    /**
     * Method used to represent that a new trial has started.
     * @param nGame int - Number of the new game.
     */
    @Override
    public synchronized VectorClock newGame(int nGame, VectorClock vc){
        clocks.update(vc);
        String toWrite = "";
        if(knockout.equals("newGame")){
            toWrite += "Game "+ (nGame+1);
            toWrite += "\n";
        
            pw.write(toWrite);
            pw.flush();
            Update u = new Update(toWrite, clocks.toIntArray());
            updates.add(u);
            
            printHeader(clocks);
            return clocks.clone();
        }
        if(!knockout.equals("X")){
            toWrite += "Game "+(nGame)+" was won by team "+knockout+" by knock out in "+ trialN +" trials.\n";
        }
        if(knockout.equals("X")){
            if(trialsA == trialsB){
                toWrite += "Game "+(nGame)+" was a draw.\n";
            }
            else if(trialsA>trialsB){
                toWrite += "Game "+(nGame)+" was won by team A by points.\n";
            }
            else if(trialsA<trialsB){
                toWrite += "Game "+(nGame)+" was won by team B by points.\n";
            }
        }
        toWrite += "Game "+ (nGame+1) + "\n";
        
        pw.write(toWrite);
        pw.flush();
        Update u = new Update(toWrite, clocks.toIntArray());
        updates.add(u);
        
        printHeader(clocks);
        return clocks.clone();
    }
    
    /**
     * Method used to represent that a new trial has started.
     * @param nTrial int - Number of the new trial.
     */
    @Override
    public synchronized VectorClock newTrial(int nTrial, VectorClock vc){
        clocks.update(vc);
        trialN = nTrial;
        return clocks.clone();
    }
    
    /**
     * Method to set the rope value.
     * @param rope int - The rope value.
     */
    @Override
    public synchronized VectorClock setRope(int rope, VectorClock vc){
        clocks.update(vc);
        ropePos = rope;
        return clocks.clone();
    }

    /**
     * Method used to set the trial wins.
     * @param wins int[] - Trial wins.
     * @param knockout String - Representing if a knockout existed in that trial.
     */
    @Override
    public synchronized VectorClock setWins(int[] wins, String knockout, VectorClock vc){
        clocks.update(vc);
        trialsA = wins[0];
        trialsB = wins[1];
        this.knockout = knockout;
        return clocks.clone();
    }

    /**
     * Method used to set the game wins.
     * @param gameWins int[] - Wins for both teams.
     * @param nGame int - Number of the game the wins are refering.
     */
    @Override
    public synchronized VectorClock setGameWins(int[] gameWins, int nGame, VectorClock vc){
        clocks.update(vc);
        gamesA = gameWins[0];
        gamesB = gameWins[1];
        finishMatch(nGame, clocks);
        return clocks.clone();
    }
    
    /**
     * Method used to print the final line that declares the winner
     * @param nGame int - Number of the last game
     */
    private void finishMatch(int nGame, VectorClock vc){
        String toWrite = "";
        if(!knockout.equals("X")){
            toWrite += "Game "+(nGame)+" was won by team "+knockout+" by knock out in "+ trialN +" trials.\n";
        }
        if(knockout.equals("X")){
            if(trialsA == trialsB){
                toWrite += "Game "+(nGame)+" was a draw.\n";
            }
            else if(trialsA>trialsB){
                toWrite += "Game "+(nGame)+" was won by team A by points.\n";
            }
            else if(trialsA<trialsB){
                toWrite += "Game "+(nGame)+" was won by team B by points.\n";
            }
        }
        if(gamesA == gamesB){
            toWrite += "Match was a draw.";
        }
        else if(gamesA > gamesB){
            toWrite += "Match was won by team A ("+gamesA+"-"+gamesB+").";
        }
        else if(gamesA < gamesB){
            toWrite += "Match was won by team B ("+gamesA+"-"+gamesB+").";
        }
        
        pw.write(toWrite);
        pw.flush();
        Update u = new Update(toWrite, vc.toIntArray());
        updates.add(u);
        
        Map<Integer, Update> tab = new Hashtable<>();
        for (int i = 0; i < this.updates.size(); i++) {
            tab.put(i, updates.get(i));
        }
        ArrayList<Map.Entry<Integer, Update>> l = new ArrayList(tab.entrySet());
        Collections.sort(l, new Comparator<Map.Entry<Integer, Update>>()
        {
            @Override
            public int compare(Map.Entry<Integer, Update> o1, Map.Entry<Integer, Update> o2) 
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        
        for (int i = 0; i < l.size(); i++) {
            reorder.write(l.get(l.size()-i-1).getValue().getText());
            int a = 0;
        }

        reorder.flush();
        reorder.close();
        
        pw.flush();
        pw.close();
        
    }

    private void config() {
        ConfigRepository conf = new ConfigRepository();
        GeneralRepositoryConfig settings = conf.getGeneralRepositoryConfig();
        
        nCoaches = settings.getnCoaches();
        nTeamPlayers = settings.getnTeamPlayers();
        nTrialPlayers = settings.getNtrialPlayers();
    }
}
