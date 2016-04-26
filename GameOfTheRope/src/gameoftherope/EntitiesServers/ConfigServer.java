/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.EntitiesHandlers.ConfigHandler;
import gameoftherope.Protocols.ConfigServerProtocol;
import gameoftherope.Regions.ConfigRepository;
import gameoftherope.Configs.ConfigRepositoryConfig;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class ConfigServer {
    public static void main(String[] args) {
        System.err.println("Started Server");
        ConfigRepository conf = new ConfigRepository();
        
        ConfigServerProtocol csp = new ConfigServerProtocol(conf);
        
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
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
            }
            
            System.err.println("Connection Accepted");
            
            handler = new ConfigHandler(commSocket, csp);
            handler.start();
        }
    }
}
