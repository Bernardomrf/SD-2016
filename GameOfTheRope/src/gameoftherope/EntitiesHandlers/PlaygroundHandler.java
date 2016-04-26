/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntitiesHandlers;

import gameoftherope.Protocols.PlaygroundProtocol;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class PlaygroundHandler extends Thread {

    private Socket socket;
    private PlaygroundProtocol protocol;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;

    public PlaygroundHandler(Socket commSocket, PlaygroundProtocol rsp) {
        socket = commSocket;
        protocol = rsp;

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
