/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Servers;

import EntitiesProxy.Handlers.BenchHandler;
import gameoftherope.Protocols.BenchProtocol;
import gameoftherope.Regions.Bench;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class BenchServer {
    public static void main(String[] args) {
        //Map<String, Integer> mainConfigs = ConfigRepository.getBenchConfigs();
        System.err.println("Started Server");
        Bench bench = new Bench();
        
        BenchProtocol bp = new BenchProtocol(bench);
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        //int portNumb = mainConfigs.get("refSitePort");                           // número do port em que o serviço é estabelecido
        int portNumb = 22131;
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            System.err.println("Waiting for connection");
            Socket commSocket = null;
            BenchHandler handler = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
                
            }
            System.err.println("Connection Accepted");
            
            handler = new BenchHandler(commSocket, bp);
            handler.start();
        }
    }
}
