/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class for the Playground Proxy.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlaygroundProxy implements IPlaygroundCoach, IPlaygroundPlayer, IPlaygroundRef{

    Socket playgroundSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    /**
     * Constructor for Playground Proxy class.
     *
     * @param configHostName String - Host name of the Config Repositotry.
     * @param portNum int - Port Number of the Config Repositotry.
     */
    public PlaygroundProxy(String configHostName, int portNum){
        ConfigProxy conf = new ConfigProxy(configHostName, portNum);
        PlaygroundConfig settings = conf.getPlaygroundConfig();
        
        String hostName = settings.getPlaygroundHostName();
        int port = settings.getPlaygroundPort();

        try {
            playgroundSocket = new Socket(hostName, port);
        } catch (IOException ex) {
        }
                                          
        try {
            in = new ObjectInputStream (playgroundSocket.getInputStream());
        } catch (IOException ex) {
        }
        try {
            out = new ObjectOutputStream (playgroundSocket.getOutputStream());
        } catch (IOException ex) {
        }
    }
    
    /**
     * Stub method for waitForTrial to allow its remote invocation on the server.
     *
     */
    @Override
    public void waitForTrial() {
        outObject = "waitForTrial- ";
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
     * Stub method for standInPosition to allow its remote invocation on the server.
     *
     * @return Object with the standInPosition return
     */
    @Override
    public int standInPosition() {
        outObject = "standInPosition- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (int) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }  
        return (int) inObject;
    }

    /**
     * Stub method for pullTheRope to allow its remote invocation on the server.
     *
     * @param strenght - Player strength
     * @param team - Player team
     */
    @Override
    public void pullTheRope(int strenght, String team) {
        outObject = "pullTheRope";
        outObject += "-" + strenght + ";" + team;
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
     * Stub method for iamDone to allow its remote invocation on the server.
     *
     */
    @Override
    public void iamDone() {
        outObject = "iamDone- ";
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
     * Stub method for callTrial to allow its remote invocation on the server.
     *
     */
    @Override
    public void callTrial() {
        outObject = "callTrial- ";
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
     * Stub method for startTrial to allow its remote invocation on the server.
     *
     */
    @Override
    public void startTrial() {
        outObject = "startTrial- ";
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
     * Stub method for waitForTrialConclusion to allow its remote invocation on the server.
     *
     */
    @Override
    public void waitForTrialConclusion() {
        outObject = "waitForTrialConclusion- ";
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
     * Stub method for assertTrialDecision to allow its remote invocation on the server.
     *
     */
    @Override
    public void assertTrialDecision() {
        outObject = "assertTrialDecision- ";
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
     * Stub method for checkKnockout to allow its remote invocation on the server.
     *
     * @return Object with the checkKnockout return
     */
    @Override
    public String checkKnockout() {
        outObject = "checkKnockout- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }  
        return (String) inObject;
    }

    /**
     * Stub method for getRope to allow its remote invocation on the server.
     *
     * @return Object with the getRope return
     */
    @Override
    public int getRope() {
        outObject = "getRope- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (int) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }  
        return (int) inObject;
    }

    /**
     * Stub method for getWins to allow its remote invocation on the server.
     *
     * @return Object with the getWins return
     */
    @Override
    public int[] getWins() {
        outObject = "getWins- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (int[]) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }  
        return (int[]) inObject;
    }

    /**
     * Stub method for getGameWins to allow its remote invocation on the server.
     *
     * @return Object with the getGameWins return     
     */
    @Override
    public int[] getGameWins() {
        outObject = "getGameWins- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (int[]) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }  
        return (int[]) inObject;
    }
    
    /**
     * Stub method for close to allow its remote invocation on the server.
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
