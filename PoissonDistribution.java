/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroidfieldsimulation;

import java.util.Random;

/**
 *
 * @author cBeezy
 */
public class PoissonDistribution {
    Random rand = new Random();
    public double nextPoisson(double mean){
        double input = rand.nextDouble();
        double output;
        if(0 <= input && 0.1 > input){
            output = 0;
        }else if(0.1 <= input && 0.2 > input){
            output = 0.104;
        }else if(0.2 <= input && 0.3 > input){
            output = 0.222;
        }else if(0.3 <= input && 0.4 > input){
            output = 0.355;
        }else if(0.4 <= input && 0.5 > input){
            output = 0.509;
        }else if(0.5 <= input && 0.6 > input){
            output = 0.690;
        }else if(0.6 <= input && 0.7 > input){
            output = 0.915;
        }else if(0.7 <= input && 0.75 > input){
            output = 1.20;
        }else if(0.75 <= input && 0.80 > input){
            output = 1.38;
        }else if(0.80 <= input && 0.84 > input){
            output = 1.60;
        }else if(0.84 <= input && 0.88 > input){
            output = 1.83;
        }else if(0.88 <= input && 0.995 > input){
            output = 2.12;
        }else if(0.995 <= input && 0.998 > input){
            output = 5.30;
        }else if(0.998 <= input && 0.999 > input){
            output = 6.20;
        }else if(0.999 <= input && 0.9997 > input){
            output = 7.0;
        }else{
            output = 8.0;
        }
        return output * mean;
    }
}
