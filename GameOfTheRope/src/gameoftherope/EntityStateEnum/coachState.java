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
public enum coachState {

    /**
     *
     */
    WAIT_REFEREE_COMMAND{
        @Override
        public String toString(){
            return "WFRC";
        }
        public String toStringState(){
            return "WAIT_REFEREE_COMMAND";
        }
    },

    /**
     *
     */
    ASSEMBLE_TEAM{
        @Override
        public String toString(){
            return "ASTM";
        }
        public String toStringState(){
            return "ASSEMBLE_TEAM";
        }
    },

    /**
     *
     */
    WATCH_TRIAL{
        @Override
        public String toString(){
            return "WCTL";
        }
        public String toStringState(){
            return "WATCH_TRIAL";
        }
    }

}

