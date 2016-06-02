/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.VectorClock.VectorClock;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Coach interaction with the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public interface IGeneralRepositoryCoach extends Remote{
    
    /**
     * Method to change the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     * @param vc
     * @throws java.rmi.RemoteException
     */
    public void changeCoachState(coachState state, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method to initializate the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     * @param vc
     * @throws java.rmi.RemoteException
     */
    public void initCoach(coachState state, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to set the players positions for the trial.
     * @param pos int[] - Array containing the players positions.
     * @param team String - Team corresponding to the positions.
     * @param vc
     * @throws java.rmi.RemoteException
     */
    public void setPlayersPositions(int[] pos, String team, VectorClock vc) throws RemoteException;
    

}
