/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

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
    private int [] playersStatesA;
    private int [] playersStatesB;
    private int [] coachesStates;
    private int refState;
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
        playersStatesA = new int[5];
        playersStatesB = new int[5];
        coachesStates = new int[2];
        refState = 0;
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
            pw.flush();
        
    }
}
