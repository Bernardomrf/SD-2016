/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesServers;

import gameoftherope.Configs.PlaygroundConfig;
import gameoftherope.Configs.RefSiteConfig;
import gameoftherope.EntitiesHandlers.RefSiteHandler;
import gameoftherope.EntitiesProxy.ConfigProxy;
import gameoftherope.Regions.RefSite;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteServer {

    /**
     *
     * @param args
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
        RefSite refSite = new RefSite(hostName, portNum);
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        ConfigProxy conf = new ConfigProxy(hostName, portNum);
        RefSiteConfig settings = conf.getRefSiteConfig();
        
        int portNumb = settings.getRefSitePort();
        
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
