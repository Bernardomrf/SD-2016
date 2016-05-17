/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.States;

/**
 *
 * @author Bruno Silva <brunomiguelsilva@ua.pt>
 */
public enum coachState {
    WAIT_REFEREE_COMMAND{
        @Override
        public String toString(){
            return "WFRC";
        }
    }, ASSEMBLE_TEAM{
        @Override
        public String toString(){
            return "ASTM";
        }
    }, WATCH_TRIAL{
        @Override
        public String toString(){
            return "WCTL";
        }
    }

}

