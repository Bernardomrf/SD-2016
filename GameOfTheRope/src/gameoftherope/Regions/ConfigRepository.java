/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigRepository {
    
    
    // Player Configs
    private final static int maxStrength = 4;
    
    // Ref Configs
    private final static int nTrials = 6;
    private final static int nGames = 3;
   
    // Bench Configs
    private final static int nTeamPlayers = 5;
    private final static int nTrialPlayers = 3;
    private final static String benchHostName = "localhost";
    private final static int benchPort = 22131;
    
    // Playground Configs
    private final static int totalTrialPlayers = nTrialPlayers * 2;
    private final static int knockOutForce = 4;
    private final static int pullTheRopeSleep = 100;
    private final static String playgroundHostName = "localhost";
    private final static int playgroundPort = 22132;
    
    // RefSite Configs
    private final static String refSiteHostName = "localhost";
    private final static int refSitePort = 22133;
    
    // Main Configs
    private final static int nCoaches = 2;
    private final static int nPlayers = nTeamPlayers*2;
    private final static int generalServerPort = 22130;
    
    // HashMaps
    static Map<String, Integer> playerConfig = new HashMap<>();
    static Map<String, Integer> refConfig = new HashMap<>();
    static Map<String, Integer> benchConfig = new HashMap<>();
    static Map<String, Integer> playgroundConfig = new HashMap<>();
    static Map<String, Integer> refSiteConfig = new HashMap<>();
    static Map<String, Integer> mainConfig = new HashMap<>();

    // Getters

    /**
     *
     * @return
     */
    public static Map<String, Integer> getPlayerConfigs(){
        playerConfig.put("maxStrength", maxStrength);
        return playerConfig;
    }
    
    /**
     *
     * @return
     */
    public static Map<String, Integer> getRefConfigs(){
        refConfig.put("nTrials", nTrials);
        refConfig.put("nGames", nGames);
        return refConfig;
    }
        
    /**
     *
     * @return
     */
    public static Map<String, Integer> getBenchConfigs(){
        benchConfig.put("nTeamPlayers", nTeamPlayers);
        benchConfig.put("nTrialPlayers", nTrialPlayers);
        benchConfig.put("nCoaches", nCoaches);
        //benchConfig.put("benchHostName", benchHostName);
        benchConfig.put("benchPort", benchPort);
        return benchConfig;
    }
    
    /**
     *
     * @return
     */
    public static Map<String, Integer> getPlaygroundConfigs(){
        playgroundConfig.put("totalTrialPlayers", totalTrialPlayers);
        playgroundConfig.put("knockOutForce", knockOutForce);
        playgroundConfig.put("nTrialsOfGameDefault", nTrials);
        playgroundConfig.put("pullTheRopeSleep", pullTheRopeSleep);
        playgroundConfig.put("nCoaches", nCoaches);
        //playgroundConfig.put("playgroundHostName", playgroundHostName);
        playgroundConfig.put("playgroundPort", playgroundPort);
        return playgroundConfig;
    }
    
    /**
     *
     * @return
     */
    public static Map<String, Integer> getRefSiteConfigs(){
        refSiteConfig.put("nCoaches", nCoaches);
        //refSiteConfig.put("refSiteHostName", refSiteHostName);
        refSiteConfig.put("refSitePort", refSitePort);
        return refSiteConfig;
    }
    
    /**
     *
     * @return
     */
    public static Map<String, Integer> getMainConfigs(){
        mainConfig.put("nCoaches", nCoaches);
        mainConfig.put("nPlayers", nPlayers);
        mainConfig.put("generalServerPort", generalServerPort);
        return mainConfig;
    }
}
