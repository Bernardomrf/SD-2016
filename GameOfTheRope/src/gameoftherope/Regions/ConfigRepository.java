/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IConfigRepository;
import gameoftherope.Regions.Configs.BenchConfig;
import gameoftherope.Regions.Configs.ConfigRepositoryConfig;
import gameoftherope.Regions.Configs.GeneralRepositoryConfig;
import gameoftherope.Regions.Configs.PlayerConfig;
import gameoftherope.Regions.Configs.PlaygroundConfig;
import gameoftherope.Regions.Configs.RefConfig;
import gameoftherope.Regions.Configs.RefSiteConfig;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
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
    
    
    @Override
    public PlayerConfig getPlayerConfig(){
        return player;
    }
    
    @Override
    public RefConfig getRefConfig(){
        return ref;
    }
    
    @Override
    public BenchConfig getBenchConfig(){
        return bench;
    }
    
    @Override
    public PlaygroundConfig getPlaygroundConfig(){
        return playground;
    }
    
    @Override
    public RefSiteConfig getRefSiteConfig(){
        return refSite;
    }
    
    @Override
    public GeneralRepositoryConfig getGeneralRepositoryConfig(){
        return repo;
    }
    
    @Override
    public ConfigRepositoryConfig getConfigRepositoryConfig(){
        return conf;
    }
}