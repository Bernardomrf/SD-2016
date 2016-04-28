/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.Configs.BenchConfig;
import gameoftherope.Configs.GeneralRepositoryConfig;
import gameoftherope.EntitiesHandlers.GeneralRepositoryHandler;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bernardo
 */
public class GeneralRepositoryServer {

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        
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
        GeneralRepository generalRepository;
        try {
            generalRepository = new GeneralRepository(hostName, portNum);
     
            boolean goOn = true;

            ServerSocket listeningSocket = null;           // socket de escuta
            ConfigProxy conf = new ConfigProxy(hostName, portNum);
            GeneralRepositoryConfig settings = conf.getGeneralRepositoryConfig();
        
            int portNumb = settings.getGeneralRepositoryPort();

            try {
                listeningSocket = new ServerSocket(portNumb);
            } catch (IOException ex) {}

            while(goOn){
                System.err.println("Waiting for connection");
                Socket commSocket = null;
                GeneralRepositoryHandler handler;

                try {
                    if(listeningSocket != null){
                        commSocket = listeningSocket.accept();
                    }  
                } catch (Exception e) {
                    System.exit(0);
                }
                System.err.println("Connection Accepted");

                handler = new GeneralRepositoryHandler(commSocket, generalRepository, listeningSocket);
                handler.start();
            }
        
        } catch (FileNotFoundException ex) {
        }
    }
}
