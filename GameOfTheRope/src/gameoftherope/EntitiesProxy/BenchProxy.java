/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IBenchPlayer;
import gameoftherope.Interfaces.IBenchRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class for the Bench Proxy.
 * 
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class BenchProxy implements IBenchCoach, IBenchPlayer, IBenchRef{
    
    Socket benchSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    /**
     * Constructor for Bench Proxy class.
     *
     * @param configHostName String - Host name of the Config Repositotry.
     * @param portNum int - Port Number of the Config Repositotry.
     */
    public BenchProxy(String configHostName, int portNum){
        ConfigProxy conf = new ConfigProxy(configHostName, portNum);
        BenchConfig settings = conf.getBenchConfig();
        
        String hostName = settings.getBenchHostName();
        int port = settings.getBenchPort();
        
        try {
            benchSocket = new Socket(hostName, port);
        } catch (IOException ex) {
        }
                                          
        try {
            in = new ObjectInputStream (benchSocket.getInputStream());
        } catch (IOException ex) {
        }
        try {
            out = new ObjectOutputStream (benchSocket.getOutputStream());
        } catch (IOException ex) {
        }
    }
    
    /**
     * Stub method to allow its remote invocation on the server.
     * @param team
     */
    @Override
    public void reviewNotes(String team) {
        outObject = "reviewNotes";
        outObject += "-" + team;
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
     * @param team
     * @return
     */
    @Override
    public int[] callContestants(String team) {
        outObject = "callContestants";
        outObject += "-" + team;
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
     */
    @Override
    public void waitForRefCommand() {
        outObject = "waitForRefCommand- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasMatchFinished() {
        outObject = "hasMatchFinished- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (boolean) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (boolean) inObject;
    }

    /**
     *
     * @param team
     */
    @Override
    public void playersReady(String team) {
        outObject = "playersReady";
        outObject += "-" + team;
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     * @param team
     * @param id
     * @return
     */
    @Override
    public boolean seatAtTheBench(String team, int id) {
        outObject = "seatAtTheBench";
        outObject += "-" + team + ";" + id;
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (boolean) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (boolean) inObject;
    }

    /**
     *
     * @param team
     */
    @Override
    public void seatDown(String team) {
        outObject = "seatDown";
        outObject += "-" + team;
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     * @param team
     */
    @Override
    public void followCoachAdvice(String team) {
        outObject = "followCoachAdvice";
        outObject += "-" + team;
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void signalCoaches() {
        outObject = "signalCoaches- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void setMatchFinish() {
        outObject = "setMatchFinish- ";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
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

    /**
     *
     */
    @Override
    public void waitForPlayers() {
        outObject = "waitForPlayers";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

    /**
     *
     */
    @Override
    public void waitForCoaches() {
        outObject = "waitForCoaches";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
