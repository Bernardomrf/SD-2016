/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Servers;

import java.net.ServerSocket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class generalServer {
    
    public static void main(String[] args) {
        ServerSocket listeningSocket = null;           // socket de escuta
        int portNumb = 4000;                           // número do port em que o serviço é estabelecido
        GameOfTheRopeProtocol prot = new GameOfTheRopeProtocol();
    }
    
}
