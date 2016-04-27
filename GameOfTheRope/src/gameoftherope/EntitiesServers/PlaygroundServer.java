/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.EntitiesHandlers.PlaygroundHandler;
import gameoftherope.Protocols.PlaygroundProtocol;
import gameoftherope.Regions.Playground;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class PlaygroundServer {
    public static void main(String[] args) {
        //Map<String, Integer> mainConfigs = ConfigRepository.getRefSiteConfigs();
        System.err.println("Started Server");
        Playground playground = new Playground();
        
        PlaygroundProtocol rfp = new PlaygroundProtocol(playground);
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        //int portNumb = mainConfigs.get("refSitePort");                           // número do port em que o serviço é estabelecido
        int portNumb = 22132;
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            PlaygroundHandler handler = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
                System.exit(0);
            }
            System.err.println("Connection Accepted");
            
            handler = new PlaygroundHandler(commSocket, rfp, listeningSocket);
            handler.start();
        }
    }
}
