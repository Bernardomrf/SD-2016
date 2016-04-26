/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntityStateEnum;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public enum refState {
    
    /**
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
