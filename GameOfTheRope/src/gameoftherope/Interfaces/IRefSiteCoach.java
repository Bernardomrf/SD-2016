/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

/**
 * Interface for the Coach interaction with the RefSite.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IRefSiteCoach {
    
    /** 
     * Method does not block and notifies the coaches.
     */
    public void informReferee();
    
    /**
     * Method unused in this implementation of the interface.
     */
    public void close();
}
