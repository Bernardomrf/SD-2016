/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy;

import gameoftherope.ConfigRepository;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteProxy implements IRefSiteCoach, IRefSiteRef{
    
    Socket refSiteSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    String outString = null;
    String inString = null;
    
    /**
     *
     */
    public RefSiteProxy(){
        Map<String, Integer> refSiteConfigs = ConfigRepository.getRefSiteConfigs();
        int port = refSiteConfigs.get("refSitePort");
        String hostName = "localhost";
        
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
        outString = "informReferee";
        try {
            out.writeObject(outString);
        } catch (IOException ex) {
        }
        try {
            inString = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        
    }

    /**
     *
     */
    @Override
    public void waitForCoach() {
        outString = "waitForCoach";
        try {
            out.writeObject(outString);
        } catch (IOException ex) {
        }
        try {
            inString = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void announceNewGame() {
        outString = "announceNewGame";
        try {
            out.writeObject(outString);
        } catch (IOException ex) {
        }
        try {
            inString = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void declareGameWinner() {
        outString = "declareGameWinner";
        try {
            out.writeObject(outString);
        } catch (IOException ex) {
        }
        try {
            inString = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void declareMatchWinner() {
        outString = "declareMatchWinner";
        try {
            out.writeObject(outString);
        } catch (IOException ex) {
        }
        try {
            inString = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }    
}
