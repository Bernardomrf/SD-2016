/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesHandlers;

import gameoftherope.Protocols.GeneralRepositoryProtocol;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public GeneralRepositoryHandler(Socket commSocket, GeneralRepositoryProtocol bp) {
        socket = commSocket;
        protocol = bp;

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
            outputLine = protocol.processInput((String)inputLine);
            inputLine = null;
            try {
                out.writeObject(outputLine);
            } catch (IOException ex) {
            }
        }
    }
}