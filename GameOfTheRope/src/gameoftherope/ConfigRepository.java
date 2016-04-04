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
    private final int maxStrength = 4;
    
    // Ref Configs
    private final int nTrials = 6;
    private final int nGames = 3;
   
    // Bench Configs
    private final int nTeamPlayers = 5;
    private final int nTrialPlayers = 3;
    
    // Playground Configs
    private final int totalTrialPlayers = nTrialPlayers * 2;
    private final int knockOutForce = 4;
    private final int nTrialsOfGameDefault = 6;
    
    // Main Configs
    private final int nCoaches = 2;
    private final int nPlayers = 10;
    
    // HashMaps
    Map<String, Integer> playerConfig;
    Map<String, Integer> refConfig;
    Map<String, Integer> benchConfig;
    Map<String, Integer> playgroundConfig;
    Map<String, Integer> mainConfig;
    
    public ConfigRepository(){
        playerConfig = new HashMap<>();
        refConfig = new HashMap<>();
        benchConfig = new HashMap<>();
        playgroundConfig = new HashMap<>();
        mainConfig = new HashMap<>();
        
        playerConfig.put("maxStrength", maxStrength);
        
        refConfig.put("nTrials", nTrials);
        refConfig.put("nGames", nGames);
                
        benchConfig.put("nTeamPlayers", nTeamPlayers);
        benchConfig.put("nTrialPlayers", nTrialPlayers);
        
        playgroundConfig.put("totalTrialPlayers", totalTrialPlayers);
        playgroundConfig.put("knockOutForce", knockOutForce);
        playgroundConfig.put("nTrialsOfGameDefault", nTrialsOfGameDefault);
        
        mainConfig.put("nCoaches", nCoaches);
        mainConfig.put("nPlayers", nPlayers);
    }
    
    public Map<String, Integer> getPlayerConfigs(){
        return playerConfig;
    }
    
    public Map<String, Integer> getRefConfigs(){
        return refConfig;
    }
        
    public Map<String, Integer> getBenchConfigs(){
        return benchConfig;
    }
    
    public Map<String, Integer> getPlaygroundConfigs(){
        return playgroundConfig;
    }
    
    public Map<String, Integer> getMainConfigs(){
        return mainConfig;
    }
}
