/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Servers;

import gameoftherope.ConfigRepository;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class generalServer {
    
    public static void main(String[] args) {
        Map<String, Integer> mainConfigs = ConfigRepository.getMainConfigs();
        
        boolean goOn = true;
        
        ServerSocket listeningSocket = null;           // socket de escuta
        int portNumb = mainConfigs.get("generalServerPort");                           // número do port em que o serviço é estabelecido
        GameOfTheRopeProtocol prot = new GameOfTheRopeProtocol();
        
        try {
            listeningSocket = new ServerSocket(portNumb);
        } catch (IOException ex) {}
        
        while(goOn){
            Socket commSocket = null;
            
            try {
                commSocket = listeningSocket.accept();
            } catch (Exception e) {
            }
            
            
        }
    }
    
}
