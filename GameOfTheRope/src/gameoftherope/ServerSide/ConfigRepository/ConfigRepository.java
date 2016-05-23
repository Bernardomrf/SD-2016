/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.ServerSide.ConfigRepository;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Configs.ConfigRepositoryConfig;
import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.Configs.PlayerConfig;
import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Configs.RefConfig;
import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.Interfaces.IConfigRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class to implement a Configuration Server.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class ConfigRepository implements IConfigRepository {
    
    private Properties prop;
    private PlayerConfig player;
    private RefConfig ref;
    private BenchConfig bench;
    private PlaygroundConfig playground;
    private RefSiteConfig refSite;
    private GeneralRepositoryConfig repo;
    private ConfigRepositoryConfig conf;
    
    /**
     * Constructor for the Configuration Server.
     */
    public ConfigRepository(){
        player = new PlayerConfig();
        ref = new RefConfig();
        prop = new Properties();
        bench = new BenchConfig();
        playground = new PlaygroundConfig();
        refSite = new RefSiteConfig();
        repo = new GeneralRepositoryConfig();
        conf = new ConfigRepositoryConfig();
        
	InputStream input;
        
        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // Assign custom settings
            
            // Player
            player.setMaxStrength(Integer.parseInt(prop.getProperty("playerMaxStrength")));
            
            // Referee
            ref.setNGames(Integer.parseInt(prop.getProperty("nGames")));
            ref.setNTrials(Integer.parseInt(prop.getProperty("nTrials")));
            
            // Bench
            bench.setnCoaches(Integer.parseInt(prop.getProperty("nCoaches")));
            bench.setnTeamPlayers(Integer.parseInt(prop.getProperty("nTeamPlayers")));
            bench.setnTrialPlayers(Integer.parseInt(prop.getProperty("nTrialPlayers")));
            bench.setBenchHostName(prop.getProperty("benchHostName"));
            bench.setBenchPort(Integer.parseInt(prop.getProperty("benchPort")));
            
            // Playground
            playground.setNcoaches(Integer.parseInt(prop.getProperty("nCoaches")));
            playground.setnTrials(Integer.parseInt(prop.getProperty("nTrials")));
            playground.setTotalTrialPlayers(2*Integer.parseInt(prop.getProperty("nTrialPlayers")));
            playground.setKnockOutForce(Integer.parseInt(prop.getProperty("knockOutForce")));
            playground.setPullTheRopeSleep(Integer.parseInt(prop.getProperty("pullTheRopeSleep")));
            playground.setPlaygroundHostName(prop.getProperty("playgroundHostName"));
            playground.setPlaygroundPort(Integer.parseInt(prop.getProperty("playgroundPort")));
            
            // Ref Site
            refSite.setnCoaches(Integer.parseInt(prop.getProperty("nCoaches")));
            refSite.setRefSiteHostName(prop.getProperty("refSiteHostName"));
            refSite.setRefSitePort(Integer.parseInt(prop.getProperty("refSitePort")));
            
            // General Repository
            repo.setnPlayers(2*Integer.parseInt(prop.getProperty("nTeamPlayers")));
            repo.setnTeamPlayers(Integer.parseInt(prop.getProperty("nTeamPlayers")));
            repo.setNtrialPlayers(Integer.parseInt(prop.getProperty("nTrialPlayers")));
            repo.setnCoaches(Integer.parseInt(prop.getProperty("nCoaches")));
            repo.setGeneralRepositoryHostName(prop.getProperty("generalRepositoryHostName"));
            repo.setGeneralRepositoryPort(Integer.parseInt(prop.getProperty("generalRepositoryPort")));
            
            // Config Repository
            conf.setConfigRepoHostName(prop.getProperty("configServerHostName"));
            conf.setConfigRepoPort(Integer.parseInt(prop.getProperty("configServerPort")));
            
	} catch (IOException ex) {
	}
    }
    
    /**
     * Method to get the players configuration fields.
     * @return PlayerConfig - The player Configuration.
     */
    @Override
    public PlayerConfig getPlayerConfig(){
        return player;
    }
    
    /**
     * Method to get the referee configuration fields.
     * @return RefConfig - The referee Configuration.
     */
    @Override
    public RefConfig getRefConfig(){
        return ref;
    }
    
    /**
     * Method to get the bench configuration fields.
     * @return BenchConfig - The bench Configuration.
     */
    @Override
    public BenchConfig getBenchConfig(){
        return bench;
    }
    
    /**
     * Method to get the playground configuration fields.
     * @return PlaygroundConfig - The playground Configuration.
     */
    @Override
    public PlaygroundConfig getPlaygroundConfig(){
        return playground;
    }
    
    /**
     * Method to get the referee site configuration fields.
     * @return RefSiteConfig - The referee site Configuration.
     */
    @Override
    public RefSiteConfig getRefSiteConfig(){
        return refSite;
    }
    
    /**
     * Method to get the general repository configuration fields.
     * @return GeneralRepositoryConfig - The general repository Configuration.
     */
    @Override
    public GeneralRepositoryConfig getGeneralRepositoryConfig(){
        return repo;
    }
    
    /**
     * Method to get the configuration server fields.
     * @return ConfigRepositoryConfig - The configuration server Configuration.
     */
    @Override
    public ConfigRepositoryConfig getConfigRepositoryConfig(){
        return conf;
    }
}