/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftherope.Entities;

import gameoftherope.Interfaces.IBenchCoach;
import gameoftherope.Interfaces.IPlaygroundCoach;

/**
 *
 * @author brunosilva
 * @author bernardoferreira
 */
public class Coach {
    
    private IBenchCoach bench;
    private IPlaygroundCoach playground;

    
    public Coach(IBenchCoach bench, IPlaygroundCoach playground){
        this.bench = bench;
        this.playground = playground;
    }
}
