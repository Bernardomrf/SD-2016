/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.EntitiesHandlers.GeneralRepositoryHandler;
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
    public static void main(String[] args){
        //Map<String, Integer> mainConfigs = ConfigRepository.getRefSiteConfigs();
        System.err.println("Started Server");
        GeneralRepository generalRepository;
        try {
            generalRepository = new GeneralRepository();
     
            boolean goOn = true;

            ServerSocket listeningSocket = null;           // socket de escuta
            //int portNumb = mainConfigs.get("refSitePort");                           // número do port em que o serviço é estabelecido
            int portNumb = 22130;

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
