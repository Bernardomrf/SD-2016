/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the Config Repository configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Config Repository are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class ConfigRepositoryConfig implements Serializable {
    private String configRepoHostName = "localhost";
    private int configRepoPort = 22134;

    /**  
     * Method to return the Config Repository host name.
     * 
     * @return String - Host name.
     */
    public String getConfigRepoHostName() {
        return configRepoHostName;
    }

    /**
     * Method to set the Config Repository host name.
     * 
     * @param configRepoHostName String - Host name.
     */
    public void setConfigRepoHostName(String configRepoHostName) {
        this.configRepoHostName = configRepoHostName;
    }

    /**  
     * Method to return the Config Repositoery port number.
     * 
     * @return int - Port number.
     */
    public int getConfigRepoPort() {
        return configRepoPort;
    }

    /**
     * Method to return the Config Repositoery port number.
     * 
     * @param configRepoPort int - Port number.
     */
    public void setConfigRepoPort(int configRepoPort) {
        this.configRepoPort = configRepoPort;
    }
}
