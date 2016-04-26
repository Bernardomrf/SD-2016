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
public enum playerState {

    /**
     *
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
     *
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
     *
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
