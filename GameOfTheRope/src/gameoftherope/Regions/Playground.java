/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Regions;

import gameoftherope.Interfaces.IPlaygroundCoach;
import gameoftherope.Interfaces.IPlaygroundPlayer;
import gameoftherope.Interfaces.IPlaygroundRef;

/**
 *
 * @author brunosilva
 */
public class Playground implements IPlaygroundCoach, IPlaygroundPlayer, IPlaygroundRef{

    public Playground(){
        
    }
    
    @Override
    public void informReferee() {

    }

    @Override
    public void pullTheRope(int strenght, String team) {

    }

    @Override
    public void callTrial() {

    }

    @Override
    public void startTrial() {

    }

    @Override
    public void assertTrialDecision() {

    }

    @Override
    public void iamDone() {

    }

    @Override
    public void waitForTrial() {

    }

    @Override
    public void standInPosition() {

    }

    @Override
    public void waitForCoach() {

    }

    @Override
    public void waitForTrialConclusion() {

    }

    @Override
    public boolean hasGameFinish() {
        return false;
    }
    
    @Override
    public void setGameFinish() {

    }
    
}
