/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.Configs.ConfigRepositoryConfig;
import gameoftherope.EntitiesHandlers.ConfigHandler;
import gameoftherope.Regions.ConfigRepository;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main class to execute the ConfigRepository server.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class ConfigServer {

    /**
     * Main method to execute the server.
     * @param args String[] - Args required: configServer hostname, configServerPort. If not provided defaults are used.
     */
    public static void main(String[] args) {
        System.err.println("Started Server");
        ConfigRepository conf = new ConfigRepository();
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;
        ConfigRepositoryConfig c = conf.getConfigRepositoryConfig();
        int portNumb = c.getConfigRepoPort();
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            ConfigHandler handler;
            
            try {
                if (listeningSocket != null){
                    commSocket = listeningSocket.accept();
                } 
            } catch (Exception e) {
                System.exit(0);
            }
            
            System.err.println("Connection Accepted");
            
            handler = new ConfigHandler(commSocket, conf, listeningSocket);
            handler.start();
        }
    }
}
