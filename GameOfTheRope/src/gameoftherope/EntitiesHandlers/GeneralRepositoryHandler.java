/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesHandlers;

import gameoftherope.EndOfTransactionException;
import gameoftherope.Protocols.GeneralRepositoryProtocol;
import gameoftherope.Regions.GeneralRepository;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bernardo
 */
public class GeneralRepositoryHandler extends Thread{
    private Socket socket;
    private GeneralRepositoryProtocol protocol;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private ServerSocket listeningSocket;

    /**
     *
     * @param commSocket
     * @param repo
     * @param listeningSocket
     */
    public GeneralRepositoryHandler(Socket commSocket, GeneralRepository repo, ServerSocket listeningSocket) {
        socket = commSocket;
        protocol = new GeneralRepositoryProtocol(repo);
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
