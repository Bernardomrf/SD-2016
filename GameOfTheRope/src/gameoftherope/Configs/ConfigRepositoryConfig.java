/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigRepositoryConfig implements Serializable {
    private String configRepoHostName = "localhost";
    private int configRepoPort = 22134;

    /**
     * @return the configRepoHostName
     */
    public String getConfigRepoHostName() {
        return configRepoHostName;
    }

    /**
     * @param configRepoHostName the configRepoHostName to set
     */
    public void setConfigRepoHostName(String configRepoHostName) {
        this.configRepoHostName = configRepoHostName;
    }

    /**
     * @return the configRepoPort
     */
    public int getConfigRepoPort() {
        return configRepoPort;
    }

    /**
     * @param configRepoPort the configRepoPort to set
     */
    public void setConfigRepoPort(int configRepoPort) {
        this.configRepoPort = configRepoPort;
    }
}
