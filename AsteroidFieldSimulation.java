/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfieldsimulation;

import static java.lang.Thread.sleep;

/**
 *
 * @author cory
 */
public class AsteroidFieldSimulation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        int max_storage = 5;
        int max_shields = 5;
        int num_asteroids = 55;
        int num_probes = 20;

        
        TaskForceCommand command = new TaskForceCommand();
        command.Init(max_storage, max_shields, num_asteroids, num_probes);
        command.start();
        
        
        
    }
    
}
