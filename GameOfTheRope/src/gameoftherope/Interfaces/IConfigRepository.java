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
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IConfigRepository {

    /**
     *
     * @return
     */
    public BenchConfig getBenchConfig();

    /**
     *
     * @return
     */
    public ConfigRepositoryConfig getConfigRepositoryConfig();

    /**
     *
     * @return
     */
    public GeneralRepositoryConfig getGeneralRepositoryConfig();

    /**
     *
     * @return
     */
    public PlayerConfig getPlayerConfig();

    /**
     *
     * @return
     */
    public PlaygroundConfig getPlaygroundConfig();

    /**
     *
     * @return
     */
    public RefConfig getRefConfig();

    /**
     *
     * @return
     */
    public RefSiteConfig getRefSiteConfig();
    
    /**
     *
     */
    public void close();
    
}
