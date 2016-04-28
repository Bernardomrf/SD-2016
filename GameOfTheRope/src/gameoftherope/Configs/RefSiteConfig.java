/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Configs;

import java.io.Serializable;

/**
 * Class to set the Ref Site configurations.
 * If the configuration file (config.properties) is not found,
 * the settings of the Ref Site are the ones on this class.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class RefSiteConfig implements Serializable {
    private int nCoaches = 2;
    private String refSiteHostName = "localhost";
    private int refSitePort = 22133;

    /**  
     * Method to return the Ref Site host name.
     * 
     * @return String - Host name.
     */
    public String getRefSiteHostName() {
        return refSiteHostName;
    }

    /**
     * Method to set the Ref Site host name.
     * 
     * @param refSiteHostName String - Host name.
     */
    public void setRefSiteHostName(String refSiteHostName) {
        this.refSiteHostName = refSiteHostName;
    }

    /**  
     * Method to return the Ref Site port number.
     * 
     * @return int - Port number.
     */
    public int getRefSitePort() {
        return refSitePort;
    }

    /**
     * Method to set the Ref Site port number.
     * 
     * @param refSitePort int - Port number.
     */
    public void setRefSitePort(int refSitePort) {
        this.refSitePort = refSitePort;
    }

    /**  
     * Method to return the number of coaches.
     * 
     * @return int - Number of coaches.
     */
    public int getnCoaches() {
        return nCoaches;
    }

    /**
     * Method to set the number of coaches.
     * 
     * @param nCoaches int - Number of coaches.
     */
    public void setnCoaches(int nCoaches) {
        this.nCoaches = nCoaches;
    }
}
