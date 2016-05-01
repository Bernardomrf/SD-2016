/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntityStateEnum;

/**
 *
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public enum refState {
    
    /**
     * Start of the match state representation
     */
    START_OF_THE_MATCH{
        @Override
        public String toString(){
            return "SOM";
        }
        public String toStringState(){
            return "START_OF_THE_MATCH";
        }
    },

    /**
     * Start of a game state representation
     */
    START_OF_A_GAME{
        @Override
        public String toString(){
            return "SOF";
        }
        public String toStringState(){
            return "START_OF_A_GAME";
        }
    },

    /**
     * Teams ready state representation
     */
    TEAMS_READY{
        @Override
        public String toString(){
            return "TRY";
        }
        public String toStringState(){
            return "TEAMS_READY";
        }
    },

    /**
     * Wait for trial conclusion state representation
     */
    WAIT_FOR_TRIAL_CONCLUSION{
        @Override
        public String toString(){
            return "WTC";
        }
        public String toStringState(){
            return "WAIT_FOR_TRIAL_CONCLUSION";
        }
    },

    /**
     * End of a game state representation
     */
    END_OF_A_GAME{
        @Override
        public String toString(){
            return "EOG";
        }
        public String toStringState(){
            return "END_OF_A_GAME";
        }
    },

    /**
     * End of the match state representation
     */
    END_OF_THE_MATCH{
        @Override
        public String toString(){
            return "EOM";
        }
        public String toStringState(){
            return "END_OF_THE_MATCH";
        }
    }
    
}
