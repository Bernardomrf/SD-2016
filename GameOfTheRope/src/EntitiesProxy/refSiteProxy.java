/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy;

import gameoftherope.ConfigRepository;
import gameoftherope.Interfaces.IRefSiteCoach;
import gameoftherope.Interfaces.IRefSiteRef;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class refSiteProxy implements IRefSiteCoach, IRefSiteRef{
    
    Socket refSiteSocket = null;
    
    ObjectInputStream in = null;                 
    ObjectOutputStream out = null;
    
    public refSiteProxy(){
        Map<String, Integer> refSiteConfigs = ConfigRepository.getRefSiteConfigs();
        int port = refSiteConfigs.get("refSitePort");
        
        try {
            refSiteSocket = new Socket("localhost", port);
        } catch (IOException ex) {
        }
                                          
        try {
            in = new ObjectInputStream (refSiteSocket.getInputStream());
        } catch (IOException ex) {
        }
        try {
            out = new ObjectOutputStream (refSiteSocket.getOutputStream());
        } catch (IOException ex) {
        }
    }
    
    @Override
    public void informReferee() {
        try {
            out.writeObject("informReferee");
        } catch (IOException ex) {
        }
    }

    @Override
    public void waitForCoach() {
        try {
            out.writeObject("informReferee");
        } catch (IOException ex) {
        }
    }

    @Override
    public void announceNewGame() {
        try {
            out.writeObject("informReferee");
        } catch (IOException ex) {
        }
    }

    @Override
    public void declareGameWinner(String knockOut) {
        try {
            out.writeObject("informReferee-"+knockOut);
        } catch (IOException ex) {
        }
    }

    @Override
    public void declareMatchWinner() {
        try {
            out.writeObject("informReferee");
        } catch (IOException ex) {
        }
    }
    
}
