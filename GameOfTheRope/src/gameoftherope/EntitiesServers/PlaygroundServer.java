/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.EntitiesHandlers.PlaygroundHandler;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.Regions.Playground;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main class to execute the Playground server.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class PlaygroundServer {

    /**
     * Main method to execute the server.
     * @param args String[] - Args required: configServer hostname, configServerPort. If not provided defaults are used.
     */
    public static void main(String[] args) {

        String hostName;
        int portNum;
        System.err.println("Started Server");
        if(args.length!=2){
            hostName = "localhost";
            portNum = 22134;
        }
        else{
            hostName = args[0];
            portNum = Integer.parseInt(args[1]);
        }        
        Playground playground = new Playground(hostName, portNum);
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        ConfigProxy conf = new ConfigProxy(hostName, portNum);
        PlaygroundConfig settings = conf.getPlaygroundConfig();
        
        int portNumb = settings.getPlaygroundPort();
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            PlaygroundHandler handler;
            
            try {
                if(listeningSocket != null){
                    commSocket = listeningSocket.accept();
                }
            } catch (Exception e) {
                System.exit(0);
            }
            System.err.println("Connection Accepted");
            
            handler = new PlaygroundHandler(commSocket, playground, listeningSocket);
            handler.start();
        }
    }
}
