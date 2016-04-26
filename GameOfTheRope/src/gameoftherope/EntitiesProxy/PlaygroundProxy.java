/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;
import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Regions.ConfigRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class PlaygroundProxy implements IPlaygroundCoach, IPlaygroundPlayer, IPlaygroundRef{

    Socket playgroundSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    /**
     *
     */
    public PlaygroundProxy(){
        ConfigProxy conf = new ConfigProxy();
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
     *
     * @return
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
     *
     * @param strenght
     * @param team
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
     *
     * @return
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
     *
     * @return
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
     *
     * @return
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
     *
     * @return
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
    
}
