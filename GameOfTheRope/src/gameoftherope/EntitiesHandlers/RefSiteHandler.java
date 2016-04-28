/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesHandlers;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Protocols.RefSiteProtocol;
import gameoftherope.Regions.RefSite;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteHandler extends Thread {

    private Socket socket;
    private RefSiteProtocol protocol;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private ServerSocket listeningSocket;

    /**
     *
     * @param commSocket
     * @param refSite
     * @param listeningSocket
     */
    public RefSiteHandler(Socket commSocket, RefSite refSite, ServerSocket listeningSocket) {
        socket = commSocket;
        protocol = new RefSiteProtocol(refSite);
        this.listeningSocket = listeningSocket;
        
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        }
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        boolean end = false;
        Object inputLine = null; 
        Object outputLine = null;

        while (!end) {
            try {
                inputLine = (String) in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
            }
            try {
                outputLine = protocol.processInput((String)inputLine);
            } catch (UnsupportedOperationException ex) {
            } catch (EndOfTransactionException ex) {
                try {
                    if(!listeningSocket.isClosed()){
                        listeningSocket.close();
                    }
                } catch (IOException ex1) {
                }
                break;
            }
            inputLine = null;
            try {
                out.writeObject(outputLine);
            } catch (IOException ex) {
            }
        }
    }
}
