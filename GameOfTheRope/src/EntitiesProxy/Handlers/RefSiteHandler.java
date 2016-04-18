/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntitiesProxy.Handlers;

import gameoftherope.Regions.RefSite;
import java.net.Socket;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public class RefSiteHandler extends Thread{
    private RefSite refSite;
    private Socket socket;
    
    public RefSiteHandler(RefSite site, Socket commSocket){
        refSite = site;
        socket = commSocket;
    }
    
    @Override
    public void run(){
        
    }
}
