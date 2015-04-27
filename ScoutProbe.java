/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfieldsimulation;

import static java.lang.Math.log;
import java.util.Random;

/**
 *
 * @author cory
 */
public class ScoutProbe extends Thread{
    private int id;
    private Asteroid curr_asteroid;
    private int next_asteroid_id;
    private int next_asteroid_mass;
    private double next_asteroid_discovered;
    private double next_asteroid_detection;
    private boolean fleet_destroyed;
    private String curr_order;
    private Random rand = new Random();
    private PoissonDistribution  p_dist = new PoissonDistribution();
    private TaskForceCommand command;

    ScoutProbe(int i , TaskForceCommand t){
        id = i;
        curr_order = "";
        curr_asteroid = null;
        next_asteroid_id = 0;
        command = t;
    }
    
    
    public void run(){
        curr_order = command.giveOrder(id,curr_order);
        while(curr_order.equals("ACQUIRE_NEXT_TARGET")){
            next_asteroid_id++;
            next_asteroid_detection = p_dist.nextPoisson(4.0) * 1000;
            System.out.println("Detection: " + next_asteroid_detection);
            try{
                Thread.sleep(((int)next_asteroid_detection));
            }catch(InterruptedException e){}
            curr_asteroid = new Asteroid(next_asteroid_id, System.currentTimeMillis());
            System.out.println("Scout\t\t->\tfound a new asteroid");
            
            curr_order = command.giveOrder(id,curr_order);
            if(curr_order.equals("REPORT_TARGET")){
                command.reportAsteroid(curr_asteroid);
            }else break;            
            curr_order = command.giveOrder(id, curr_order);
        }
        System.out.println("Scout\t\t->\tAbandoning Asteroid Search");
    }
}
