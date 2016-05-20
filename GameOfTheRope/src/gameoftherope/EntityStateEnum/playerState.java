/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.EntityStateEnum;

/**
 * Enumerator for the player states
 * @author Bruno Silva [brunomiguelsilva@ua.pt]
 * @author Bernardo Ferreira [bernardomrferreira@ua.pt]
 */
public enum playerState {

    /**
     *  Seat at the bench state representation
     */
    SEAT_AT_THE_BENCH{
        @Override
        public String toString(){
            return "SAB";
        }
        public String toStringState(){
            return "SEAT_AT_THE_BENCH";
        }
    },

    /**
     * Stand in position state representation
     */
    STAND_IN_POSITION{
        @Override
        public String toString(){
            return "SIP";
        }
        public String toStringState(){
            return "STAND_IN_POSITION";
        }
    },

    /**
     * Do your best state representation
     */
    DO_YOUR_BEST{
        @Override
        public String toString(){
            return "DYB";
        }
        public String toStringState(){
            return "DO_YOUR_BEST";
        }
    }

}
