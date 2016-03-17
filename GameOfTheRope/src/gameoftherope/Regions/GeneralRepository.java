/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.coachState;
import gameoftherope.playerState;
import gameoftherope.refState;
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
    
    private int [] playersStrengthA;
    private int [] playersStrengthB;
    private playerState [] playersStatesA;
    private playerState [] playersStatesB;
    private coachState [] coachesStates;
    private refState refereeState;
    private int [] trialPosA;
    private int [] trialPosB;
    private int trialN;
    private int ropePos;
    private int games;
    private int gamesA;
    private int gamesB;

    private final File log;
    private String filename;
    private static PrintWriter pw;
    
    public GeneralRepository() throws FileNotFoundException{
        playersStrengthA = new int[5];
        playersStrengthB = new int[5];
        playersStatesA = new playerState[5];
        playersStatesB = new playerState[5];
        coachesStates = new coachState[2];
        refereeState = null;
        trialPosA = new int[3];
        trialPosB = new int[3];
        trialN = 0;
        ropePos = 0;
        games = 0;
        gamesA = 0;
        gamesB = 0;
        
        
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddhhmmss");
        this.filename = "GameOfTheRope_" + date.format(today) + ".log";
        
        this.log = new File(this.filename);
        pw = new PrintWriter(log);
        pw.println("                               Game of the Rope - Description of the internal state");
        pw.println();
    }
    
    public synchronized void printHeader(){
            pw.println("Ref Coa 1 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5 Coa 2 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5       Trial");
            pw.println("Sta  Stat Sta SG Sta SG Sta SG Sta SG Sta SG  Stat Sta SG Sta SG Sta SG Sta SG Sta SG 3 2 1 . 1 2 3 NB PS");
    }
    
    public synchronized void changeRefState(refState state){
        refereeState = state;
        printLine();
    }
    
    public synchronized void changePlayerState(playerState state, int id, String team){
        if (team.equals("A")){
            if (state == playersStatesA[id]) {
                return;
            }
            playersStatesA[id] = state;
        }
        else if (team.equals("B")){
            if (state == playersStatesB[id]) {
                return;
            }
            playersStatesB[id] = state;
        }
        
        printLine();
    }
    
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
    
    public synchronized void initCoach(coachState state, String team){
        if (team.equals("A")){
            coachesStates[0] = state;
        }
        else if (team.equals("B")){
            coachesStates[1] = state;
        }        
    }
    
    public synchronized void initRef(refState state){
        refereeState = state;
    }
    
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
        pw.println("- - - . - - - -- --");
                    
        pw.flush();

    }
}
