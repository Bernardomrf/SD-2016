/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesProxy;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Configs.ConfigRepositoryConfig;
import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.Configs.PlayerConfig;
import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Configs.RefConfig;
import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.Interfaces.IConfigRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigProxy implements IConfigRepository{
    Socket benchSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    Object outObject = null;
    Object inObject = null;
    
    public ConfigProxy(){
        
        int port = 22134;
        String hostName = "localhost";
        
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
    
    @Override
    public PlayerConfig getPlayerConfig(){
        outObject = "getPlayerConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (PlayerConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (PlayerConfig) inObject;
    }
    
    @Override
    public RefConfig getRefConfig(){
        outObject = "getRefConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (RefConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (RefConfig) inObject;
    }
    
    @Override
    public BenchConfig getBenchConfig(){
        outObject = "getBenchConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (BenchConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (BenchConfig) inObject;
    }
    
    @Override
    public PlaygroundConfig getPlaygroundConfig(){
        outObject = "getPlaygroundConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (PlaygroundConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (PlaygroundConfig) inObject;
    }
    
    @Override
    public RefSiteConfig getRefSiteConfig(){
        outObject = "getRefSiteConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (RefSiteConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (RefSiteConfig) inObject;
    }
    
    @Override
    public GeneralRepositoryConfig getGeneralRepositoryConfig(){
        outObject = "getGeneralRepositoryConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (GeneralRepositoryConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (GeneralRepositoryConfig) inObject;
    }
    
    @Override
    public ConfigRepositoryConfig getConfigRepositoryConfig(){
        outObject = "getConfigRepositoryConfig";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
        try {
            inObject = (ConfigRepositoryConfig) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
        }
        return (ConfigRepositoryConfig) inObject;
    }
    
    public void close(){
        outObject = "close-";
        try {
            out.writeObject(outObject);
        } catch (IOException ex) {
        }
    }
}
