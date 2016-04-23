/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Servers;

import EntitiesProxy.Handlers.GeneralRepositoryHandler;
import gameoftherope.Protocols.GeneralRepositoryProtocol;
import gameoftherope.Regions.GeneralRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            GeneralRepositoryProtocol bp = new GeneralRepositoryProtocol(generalRepository);
        
        
        
        
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
            GeneralRepositoryHandler handler = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
                
            }
            System.err.println("Connection Accepted");
            
            handler = new GeneralRepositoryHandler(commSocket, bp);
            handler.start();
        }
        
        } catch (FileNotFoundException ex) {
        }
    }
}
