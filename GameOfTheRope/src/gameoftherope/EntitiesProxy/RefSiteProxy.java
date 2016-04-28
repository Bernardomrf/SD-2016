/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class for the Referee Site Proxy.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class RefSiteProxy implements IRefSiteCoach, IRefSiteRef{
    
    Socket refSiteSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    /**
     * Constructor for RefSite Proxy class.
     *
     * @param configHostName String - Host name of the Config Repositotry.
     * @param portNum int - Port Number of the Config Repositotry.
     */
    public RefSiteProxy(String configHostName, int portNum){

        ConfigProxy conf = new ConfigProxy(configHostName, portNum);
        RefSiteConfig settings = conf.getRefSiteConfig();
        
        String hostName = settings.getRefSiteHostName();
        int port = settings.getRefSitePort();

        
        try {
            refSiteSocket = new Socket(hostName, port);
        } catch (IOException ex) {
        }
                                          
        try {
            in = new ObjectInputStream (refSiteSocket.getInputStream());
        } catch (IOException ex) {
        }
        try {
            out = new ObjectOutputStream (refSiteSocket.getOutputStream());
        } catch (IOException ex) {
        }
    }
    
    /**
     *
     */
    @Override
    public void informReferee() {
        outObject = "informReferee";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        
    }

    /**
     *
     */
    @Override
    public void waitForCoach() {
        outObject = "waitForCoach";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void announceNewGame() {
        outObject = "announceNewGame";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void declareGameWinner() {
        outObject = "declareGameWinner";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void declareMatchWinner() {
        outObject = "declareMatchWinner";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }  
    
    /**
     *
     */
    @Override
    public void close(){
        outObject = "close-";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
    }
}
