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
public enum refState {
    
    START_OF_THE_MATCH{
        @Override
        public String toString(){
            return "SOM";
        }
    }, START_OF_A_GAME{
        @Override
        public String toString(){
            return "SOF";
        }
    }, TEAMS_READY{
        @Override
        public String toString(){
            return "TRY";
        }
    }, WAIT_FOR_TRIAL_CONCLUSION{
        @Override
        public String toString(){
            return "WTC";
        }
    }, END_OF_A_GAME{
        @Override
        public String toString(){
            return "EOG";
        }
    }, END_OF_THE_MATCH{
        @Override
        public String toString(){
            return "EOM";
        }
    }
    
}
