/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.ConfigRepository;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigServerProtocol {
    private final ConfigRepository conf;
    
    public ConfigServerProtocol(ConfigRepository conf){
        this.conf = conf;
    }
    
    public Object processInput(String input) throws UnsupportedOperationException, EndOfTransactionException{
        if(input == null){
            throw new EndOfTransactionException("Close");
        }
        
        Object ret = null;
        String[] methodCall = input.split("=");
        String method = methodCall[0];
        switch (method) {
            case "getPlayerConfig":
                ret = conf.getPlayerConfig();
                break;
            case "getRefConfig":
                ret = conf.getRefConfig();
                break;
            case "getBenchConfig":
                ret = conf.getBenchConfig();
                break;
            case "getPlaygroundConfig":
                ret = conf.getPlaygroundConfig();
                break;
            case "getRefSiteConfig":
                ret = conf.getRefSiteConfig();
                break;
            case "getGeneralRepositoryConfig":
                ret = conf.getGeneralRepositoryConfig();
                break;
            case "getConfigRepositoryConfig":
                ret = conf.getConfigRepositoryConfig();
                break;
            case "close":
                throw new EndOfTransactionException("Close");
            default:
                throw new UnsupportedOperationException();
        } 
        return ret;
    }
}
