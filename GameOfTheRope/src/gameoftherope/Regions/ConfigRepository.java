/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Regions.Configs.PlayerConfig;
import gameoftherope.Regions.Configs.RefConfig;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigRepository {
    
    private PlayerConfig player = new PlayerConfig();
    private RefConfig ref = new RefConfig();
    
    
    public PlayerConfig getPlayerConfig(){
        return player;
    }
    
    public void setPlayerConfig(int max){
        player.setMaxStrength(max);
    }
    
    public RefConfig getRefConfig(){
        return ref;
    }
    
    public void setRefConfig(int max){
        ref.
    }
    
}