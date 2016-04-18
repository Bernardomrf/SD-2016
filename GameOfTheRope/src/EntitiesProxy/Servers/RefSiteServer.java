/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Servers;

import EntitiesProxy.Handlers.RefSiteHandler;
import gameoftherope.GameOfTheRopeProtocol;
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
        
        RefSite refSite = new RefSite();
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        //int portNumb = mainConfigs.get("refSitePort");                           // número do port em que o serviço é estabelecido
        int portNumb = 22133;
        GameOfTheRopeProtocol prot = new GameOfTheRopeProtocol();
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            Socket commSocket = null;
            RefSiteHandler handler = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
            }
            
            handler = new RefSiteHandler(refSite, commSocket);
            handler.start();
        }
    }
}
