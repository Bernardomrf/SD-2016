/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.Interfaces.IGeneralRepositoryCoach;
import gameoftherope.Interfaces.IGeneralRepositoryPlayer;
import gameoftherope.Interfaces.IGeneralRepositoryRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author bernardo
 */
public class GeneralRepositoryProxy implements IGeneralRepositoryCoach, IGeneralRepositoryPlayer, IGeneralRepositoryRef{
    
    Socket generalSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    /**
     *
     * @param configHostName
     * @param portNum
     */
    public GeneralRepositoryProxy(String configHostName, int portNum){
        ConfigProxy conf = new ConfigProxy(configHostName, portNum);
        GeneralRepositoryConfig settings = conf.getGeneralRepositoryConfig();
        
        String hostName = settings.getGeneralRepositoryHostName();
        int port = settings.getGeneralRepositoryPort();
        
        try {
            generalSocket = new Socket(hostName, port);
        } catch (IOException ex) {
        }
                                          
        try {
            in = new ObjectInputStream (generalSocket.getInputStream());
        } catch (IOException ex) {
        }
        try {
            out = new ObjectOutputStream (generalSocket.getOutputStream());
        } catch (IOException ex) {
        }
    }
    
    /**
     *
     */
    @Override
    public void printHeader(){
        outObject = "printHeader";
        outObject += "=";
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
     * @param state
     */
    @Override
    public void changeRefState(refState state){
        outObject = "changeRefState";
        outObject += "=" + state.name();
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
     * @param state
     * @param id
     * @param team
     * @param strength
     */
    @Override
    public void changePlayerState(playerState state, int id, String team, int strength){
        outObject = "changePlayerState";
        outObject += "=" + state.name() + ";" + id + ";" + team + ";" + strength;
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
     * @param state
     * @param team
     */
    @Override
    public void changeCoachState(coachState state, String team){
        outObject = "changeCoachState";
        outObject += "=" + state.name() + ";" + team;
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
     * @param state
     * @param strength
     * @param id
     * @param team
     */
    @Override
    public void initPlayer(playerState state, int strength, int id, String team){
        outObject = "initPlayer";
        outObject += "=" + state.name() + ";" + strength + ";" + id + ";" + team;
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
     * @param state
     * @param team
     */
    @Override
    public void initCoach(coachState state, String team){
        outObject = "initCoach";
        outObject += "=" + state.name() + ";" + team;
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
     * @param state
     */
    @Override
    public void initRef(refState state){
        outObject = "initRef";
        outObject += "=" + state.name();
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
     * @param pos
     * @param team
     */
    @Override
    public void setPlayersPositions(int[] pos, String team){
        outObject = "setPlayersPositions";
        outObject += "=";
        outObject += unrollArray(pos);
        outObject += ";" + team;
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
     * @param nGame
     */
    @Override
    public void newGame(int nGame){
        outObject = "newGame";
        outObject += "=" + nGame;
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
     * @param nTrial
     */
    @Override
    public void newTrial(int nTrial){
        outObject = "newTrial";
        outObject += "=" + nTrial;
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
     * @param rope
     */
    @Override
    public void setRope(int rope){
        outObject = "setRope";
        outObject += "=" + rope;
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
     * @param wins
     * @param knockout
     */
    @Override
    public void setWins(int[] wins, String knockout){
        outObject = "setWins";
        outObject += "=";
        outObject += unrollArray(wins);
        outObject += ";" + knockout;

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
     * @param gameWins
     * @param nGame
     */
    @Override
    public void setGameWins(int[] gameWins, int nGame){
        outObject = "setGameWins";
        outObject += "=";
        outObject += unrollArray(gameWins);
        outObject += ";" + nGame;
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (String) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
    
    private String unrollArray(int[] array){
        String result = "";
        for(int i = 0; i<array.length;i++){
            if(i==0) result += array[i];
            else result += "," + array[i];
        }
        return result;
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
