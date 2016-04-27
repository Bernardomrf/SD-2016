/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.EntitiesHandlers.RefSiteHandler;
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
        System.err.println("Started Server");
        RefSite refSite = new RefSite();
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;
        int portNumb = 22133;
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            RefSiteHandler handler;
            
            try {
                if(listeningSocket != null){
                    commSocket = listeningSocket.accept();
                }
            } catch (Exception e) {
                System.exit(0);
            }
            System.err.println("Connection Accepted");
            
            handler = new RefSiteHandler(commSocket, refSite, listeningSocket);
            handler.start();
        }
    }
}
