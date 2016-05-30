/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public interface IGeneralRepository extends Remote{
    
    /**
     * Method to change the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     * @throws java.rmi.RemoteException
     */
    public void changeCoachState(coachState state, String team) throws RemoteException;
    
    /**
     * Method to initializate the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     * @throws java.rmi.RemoteException
     */
    public void initCoach(coachState state, String team) throws RemoteException;
    
    /**
     * Method used to set the players positions for the trial.
     * @param pos int[] - Array containing the players positions.
     * @param team String - Team corresponding to the positions.
     * @throws java.rmi.RemoteException
     */
    public void setPlayersPositions(int[] pos, String team) throws RemoteException;
    
    /**
     * Method to change the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Current strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @throws java.rmi.RemoteException
     */
    public void changePlayerState(playerState state, int id, String team, int strength) throws RemoteException;
    
    /**
     * Method to initializate the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Initial strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @throws java.rmi.RemoteException
     */
    public void initPlayer(playerState state, int strength, int id, String team) throws RemoteException;
    
    /**
     * Method to change the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     * @throws java.rmi.RemoteException
     */
    public void changeRefState(refState state) throws RemoteException;
    
    /**
     * Method to initializate the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     * @throws java.rmi.RemoteException
     */
    public void initRef(refState state) throws RemoteException;
    
    /**
     * Method used to represent that a new trial has started.
     * @param nGame int - Number of the new game.
     * @throws java.rmi.RemoteException
     */
    public void newGame(int nGame) throws RemoteException;
    
    /**
     * Method used to represent that a new trial has started.
     * @param nTrial int - Number of the new trial.
     * @throws java.rmi.RemoteException
     */
    public void newTrial(int nTrial) throws RemoteException;
    
    /**
     * Method to set the rope value.
     * @param rope int - The rope value.
     * @throws java.rmi.RemoteException
     */
    public void setRope(int rope) throws RemoteException;
    
    /**
     * Method used to set the trial wins.
     * @param wins int[] - Trial wins.
     * @param knockout String - Representing if a knockout existed in that trial.
     * @throws java.rmi.RemoteException
     */
    public void setWins(int[] wins, String knockout) throws RemoteException;

    /**
     * Method used to set the game wins.
     * @param gameWins int[] - Wins for both teams.
     * @param nGame int - Number of the game the wins are refering.
     * @throws java.rmi.RemoteException
     */
    public void setGameWins(int[] gameWins, int nGame) throws RemoteException;
    
    /**
     * Method used to print the log file header.
     * @throws java.rmi.RemoteException
     */
    public void printHeader() throws RemoteException;
    
    
}
