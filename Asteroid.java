/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfield;

import java.util.Random;

/**
 *
 * @author cory
 */
public class Asteroid {
    private Random rand;
    private int id;
    private int mass;
    private float mass_variant;
    private double time_discovered;
    private double time_to_impact;
    private double time_of_impact;
    
    Asteroid(int i,double d){
        rand = new Random();
        id = i;
        mass_variant = rand.nextFloat();
        if(mass_variant <= 0.2){
            mass = 3;
        }else if(mass_variant > 0.2 && mass_variant <= 0.5){
            mass = 6;
        }else if(mass_variant > 5 && mass_variant <= 0.7){
            mass = 9;
        }else{
            mass = 11;
        }
        time_discovered = d;
        time_to_impact = rand.nextDouble() * 15.0 * 1000;
        time_of_impact = time_discovered + time_to_impact;
    }
    
    public int getId(){
        return id;
    }
    public void setMass(int m){
        mass = m;
    }
    public int getMass(){
        return mass;
    }
    public double getTimeDiscovered(){
        return time_discovered;
    }
    public double getTimeToImpact(){
        return time_to_impact;
    }
    
    public double getTimeOfImpact(){
        return time_of_impact;
    }
    
    
}
