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
public class RefSiteConfig implements Serializable {
    private int nCoaches = 2;
    private String refSiteHostName = "localhost";
    private int refSitePort = 22133;

    /**
     * @return the refSiteHostName
     */
    public String getRefSiteHostName() {
        return refSiteHostName;
    }

    /**
     * @param refSiteHostName the refSiteHostName to set
     */
    public void setRefSiteHostName(String refSiteHostName) {
        this.refSiteHostName = refSiteHostName;
    }

    /**
     * @return the refSitePort
     */
    public int getRefSitePort() {
        return refSitePort;
    }

    /**
     * @param refSitePort the refSitePort to set
     */
    public void setRefSitePort(int refSitePort) {
        this.refSitePort = refSitePort;
    }

    /**
     * @return the nCoaches
     */
    public int getnCoaches() {
        return nCoaches;
    }

    /**
     * @param nCoaches the nCoaches to set
     */
    public void setnCoaches(int nCoaches) {
        this.nCoaches = nCoaches;
    }
}
