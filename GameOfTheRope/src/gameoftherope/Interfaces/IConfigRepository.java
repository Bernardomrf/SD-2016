/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Configs.ConfigRepositoryConfig;
import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.Configs.PlayerConfig;
import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Configs.RefConfig;
import gameoftherope.Configs.RefSiteConfig;

/**
 * Interface for the Config Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IConfigRepository {

    /**
     * Method to get the bench configuration fields.
     * @return BenchConfig - The bench Configuration.
     */
    public BenchConfig getBenchConfig();

    /**
     * Method to get the configuration server fields.
     * @return ConfigRepositoryConfig - The configuration server Configuration.
     */
    public ConfigRepositoryConfig getConfigRepositoryConfig();

    /**
     * Method to get the general repository configuration fields.
     * @return GeneralRepositoryConfig - The general repository Configuration.
     */
    public GeneralRepositoryConfig getGeneralRepositoryConfig();

    /**
     * Method to get the players configuration fields.
     * @return PlayerConfig - The player Configuration.
     */
    public PlayerConfig getPlayerConfig();

    /**
     * Method to get the playground configuration fields.
     * @return PlaygroundConfig - The playground Configuration.
     */
    public PlaygroundConfig getPlaygroundConfig();

    /**
     * Method to get the referee configuration fields.
     * @return RefConfig - The referee Configuration.
     */
    public RefConfig getRefConfig();

    /**
     * Method to get the referee site configuration fields.
     * @return RefSiteConfig - The referee site Configuration.
     */
    public RefSiteConfig getRefSiteConfig();
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
    
}
