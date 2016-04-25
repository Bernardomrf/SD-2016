/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.Regions.Configs.BenchConfig;
import gameoftherope.Regions.Configs.ConfigRepositoryConfig;
import gameoftherope.Regions.Configs.GeneralRepositoryConfig;
import gameoftherope.Regions.Configs.PlayerConfig;
import gameoftherope.Regions.Configs.PlaygroundConfig;
import gameoftherope.Regions.Configs.RefConfig;
import gameoftherope.Regions.Configs.RefSiteConfig;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IConfigRepository {

    BenchConfig getBenchConfig();

    ConfigRepositoryConfig getConfigRepositoryConfig();

    GeneralRepositoryConfig getGeneralRepositoryConfig();

    PlayerConfig getPlayerConfig();

    PlaygroundConfig getPlaygroundConfig();

    RefConfig getRefConfig();

    RefSiteConfig getRefSiteConfig();
    
}
