/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Interfaces;

import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.VectorClock.VectorClock;
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
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock changeCoachState(coachState state, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method to initializate the coach state.
     * @param state coachState - the enum value corresponding to the coach state.
     * @param team String - Team of the caller coach team.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock initCoach(coachState state, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to set the players positions for the trial.
     * @param pos int[] - Array containing the players positions.
     * @param team String - Team corresponding to the positions.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock setPlayersPositions(int[] pos, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method to change the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Current strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock changePlayerState(playerState state, int id, String team, int strength, VectorClock vc) throws RemoteException;
    
    /**
     * Method to initializate the player state.
     * @param state playerState - the enum value corresponding to the player state.
     * @param strength int - Initial strength of the player.
     * @param id int - ID of the player in the team.
     * @param team String - Team of the corresponding player.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock initPlayer(playerState state, int strength, int id, String team, VectorClock vc) throws RemoteException;
    
    /**
     * Method to change the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock changeRefState(refState state, VectorClock vc) throws RemoteException;
    
    /**
     * Method to initializate the referee state.
     * @param state refState - the enum value corresponding to the referee state.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock initRef(refState state, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to represent that a new trial has started.
     * @param nGame int - Number of the new game.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock newGame(int nGame, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to represent that a new trial has started.
     * @param nTrial int - Number of the new trial.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock newTrial(int nTrial, VectorClock vc) throws RemoteException;
    
    /**
     * Method to set the rope value.
     * @param rope int - The rope value.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock setRope(int rope, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to set the trial wins.
     * @param wins int[] - Trial wins.
     * @param knockout String - Representing if a knockout existed in that trial.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock setWins(int[] wins, String knockout, VectorClock vc) throws RemoteException;

    /**
     * Method used to set the game wins.
     * @param gameWins int[] - Wins for both teams.
     * @param nGame int - Number of the game the wins are refering.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock setGameWins(int[] gameWins, int nGame, VectorClock vc) throws RemoteException;
    
    /**
     * Method used to print the log file header.
     * @param vc
     * @return 
     * @throws java.rmi.RemoteException
     */
    public VectorClock printHeader(VectorClock vc) throws RemoteException;
    
    
}
