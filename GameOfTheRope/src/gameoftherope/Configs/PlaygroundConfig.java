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
public class PlaygroundConfig implements Serializable {
    private int totalTrialPlayers = 6;
    private int knockOutForce = 4;
    private int pullTheRopeSleep = 100;
    private int nTrials = 6;
    private int ncoaches = 2;
    private String playgroundHostName = "localhost";
    private int playgroundPort = 22132;

    /**
     * @return the totalTrialPlayers
     */
    public int getTotalTrialPlayers() {
        return totalTrialPlayers;
    }

    /**
     * @param totalTrialPlayers the totalTrialPlayers to set
     */
    public void setTotalTrialPlayers(int totalTrialPlayers) {
        this.totalTrialPlayers = totalTrialPlayers;
    }

    /**
     * @return the knockOutForce
     */
    public int getKnockOutForce() {
        return knockOutForce;
    }

    /**
     * @param knockOutForce the knockOutForce to set
     */
    public void setKnockOutForce(int knockOutForce) {
        this.knockOutForce = knockOutForce;
    }

    /**
     * @return the pullTheRopeSleep
     */
    public int getPullTheRopeSleep() {
        return pullTheRopeSleep;
    }

    /**
     * @param pullTheRopeSleep the pullTheRopeSleep to set
     */
    public void setPullTheRopeSleep(int pullTheRopeSleep) {
        this.pullTheRopeSleep = pullTheRopeSleep;
    }

    /**
     * @return the playgroundHostName
     */
    public String getPlaygroundHostName() {
        return playgroundHostName;
    }

    /**
     * @param playgroundHostName the playgroundHostName to set
     */
    public void setPlaygroundHostName(String playgroundHostName) {
        this.playgroundHostName = playgroundHostName;
    }

    /**
     * @return the playgroundPort
     */
    public int getPlaygroundPort() {
        return playgroundPort;
    }

    /**
     * @param playgroundPort the playgroundPort to set
     */
    public void setPlaygroundPort(int playgroundPort) {
        this.playgroundPort = playgroundPort;
    }

    /**
     * @return the nTrials
     */
    public int getnTrials() {
        return nTrials;
    }

    /**
     * @param nTrials the nTrials to set
     */
    public void setnTrials(int nTrials) {
        this.nTrials = nTrials;
    }

    /**
     * @return the ncoaches
     */
    public int getNcoaches() {
        return ncoaches;
    }

    /**
     * @param ncoaches the ncoaches to set
     */
    public void setNcoaches(int ncoaches) {
        this.ncoaches = ncoaches;
    }
}
