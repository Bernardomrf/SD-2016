/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Regions.ConfigRepository;

/**
 * Protocol to handle messages for the Configuration Server.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class ConfigServerProtocol {
    private final ConfigRepository conf;
    
    /**
     * Constructor for the protocol.
     * @param conf ConfigRepository - Configuration repository to be used by the protocol.
     */
    public ConfigServerProtocol(ConfigRepository conf){
        this.conf = conf;
    }
    
    /**
     * Method to process messages.
     * @param input String - The message received.
     * @return The return given by the configServer.
     * @throws UnsupportedOperationException - Exception for unsupported operation.
     * @throws EndOfTransactionException - Exception for End Of Transaction.
     */
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
