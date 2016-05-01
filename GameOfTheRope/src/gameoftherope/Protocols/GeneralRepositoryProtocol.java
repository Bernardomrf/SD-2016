/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Protocols;

import gameoftherope.EndOfTransactionException;
import gameoftherope.EntityStateEnum.coachState;
import gameoftherope.EntityStateEnum.playerState;
import gameoftherope.EntityStateEnum.refState;
import gameoftherope.Regions.GeneralRepository;

/**
 * Protocol to handle messages for the General Repository.
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public class GeneralRepositoryProtocol {
    private final GeneralRepository generalRepository;
    
    /**
     * Constructor for the protocol.
     * @param generalRepository GeneralRepository - General repository to be used by the protocol.
     */
    public GeneralRepositoryProtocol(GeneralRepository generalRepository){
        this.generalRepository = generalRepository;
    }
    
    /**
     * Method to process messages.
     * @param input String - The message received.
     * @return The return given by the generalRepository.
     * @throws UnsupportedOperationException - Exception for unsupported operation.
     * @throws EndOfTransactionException - Exception for End Of Transaction.
     */
    public Object processInput(String input) throws UnsupportedOperationException, EndOfTransactionException{
        if(input == null){
            throw new EndOfTransactionException("Close");
        }
        
        String [] methodCall = input.split("=");
        String method = methodCall[0];
        refState rState;
        playerState pState;
        coachState cState;
        String team;
        String knockout;
        String [] args;
        String [] sPos;
        String [] sWins;
        int [] pos;
        int [] wins;
        int strenght, id;
        int nGame;
        int nTrial;
        int rope;
        
        switch (method) {
            case "printHeader":
                generalRepository.printHeader();
                return null;
            case "changeRefState":
                rState = refState.valueOf(methodCall[1]);
                generalRepository.changeRefState(rState);
                return null;
            case "changePlayerState":
                args = methodCall[1].split(";");
                strenght = Integer.parseInt(args[3]);
                team = args[2];
                id = Integer.parseInt(args[1]);
                pState = playerState.valueOf(args[0]);
                generalRepository.changePlayerState(pState, id, team, strenght);
                return null;
            case "changeCoachState":
                args = methodCall[1].split(";");
                team = args[1];
                cState = coachState.valueOf(args[0]);
                generalRepository.changeCoachState(cState, team);
                return null;
            case "initPlayer":
                args = methodCall[1].split(";");
                strenght = Integer.parseInt(args[1]);
                team = args[3];
                id = Integer.parseInt(args[2]);
                pState = playerState.valueOf(args[0]);
                generalRepository.initPlayer(pState, strenght, id, team);
                return null;
            case "initCoach":
                args = methodCall[1].split(";");
                team = args[1];
                cState = coachState.valueOf(args[0]);
                generalRepository.initCoach(cState, team);
                return null;
            case "initRef":
                rState = refState.valueOf(methodCall[1]);
                generalRepository.initRef(rState);
                return null;
            case "setPlayersPositions":
                args = methodCall[1].split(";");
                sPos = args[0].split(",");
                pos = new int[sPos.length];
                for(int i = 0; i < sPos.length; i++) {
                    pos[i] = Integer.parseInt(sPos[i]);
                }
                team = args[1];
                generalRepository.setPlayersPositions(pos,team);
                return null;
            case "newGame":
                nGame = Integer.parseInt(methodCall[1]);
                generalRepository.newGame(nGame);
                return null;
            case "newTrial":
                nTrial = Integer.parseInt(methodCall[1]);
                generalRepository.newTrial(nTrial);
                return null;
            case "setRope":
                rope = Integer.parseInt(methodCall[1]);
                generalRepository.setRope(rope);
                return null;
            case "setWins":
                args = methodCall[1].split(";");
                sWins = args[0].split(",");
                wins = new int[sWins.length];
                for(int i = 0; i < sWins.length; i++) {
                    wins[i] = Integer.parseInt(sWins[i]);
                }
                knockout = args[1];
                generalRepository.setWins(wins,knockout);
                return null;
            case "setGameWins":
                args = methodCall[1].split(";");
                sWins = args[0].split(",");
                wins = new int[sWins.length];
                for(int i = 0; i < sWins.length; i++) {
                    wins[i] = Integer.parseInt(sWins[i]);
                }
                nGame = Integer.parseInt(args[1]);
                generalRepository.setGameWins(wins,nGame);
                return null;
            case "close":
                throw new EndOfTransactionException("Close");
            default:
                throw new UnsupportedOperationException();
        }
        
    }
}
