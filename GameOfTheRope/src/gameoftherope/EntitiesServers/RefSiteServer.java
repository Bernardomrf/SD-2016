/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.EntitiesHandlers.RefSiteHandler;
import gameoftherope.Protocols.RefSiteProtocol;
import gameoftherope.Regions.RefSite;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteServer {
    public static void main(String[] args) {
        //Map<String, Integer> mainConfigs = ConfigRepository.getRefSiteConfigs();
        System.err.println("Started Server");
        RefSite refSite = new RefSite();
        
        RefSiteProtocol rfp = new RefSiteProtocol(refSite);
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        //int portNumb = mainConfigs.get("refSitePort");                           // número do port em que o serviço é estabelecido
        int portNumb = 22133;
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            RefSiteHandler handler = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
                
            }
            System.err.println("Connection Accepted");
            
            handler = new RefSiteHandler(commSocket, rfp, listeningSocket);
            handler.start();
        }
    }
}
