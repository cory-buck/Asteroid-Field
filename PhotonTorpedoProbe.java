/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfieldsimulation;

/**
 *
 * @author cory
 */
public class PhotonTorpedoProbe extends Thread{
    private int id;
    private int weapon_fire_rate;
    private int weapon_damage;
    private Asteroid curr_asteroid;
    private double curr_asteroid_impact_time;
    private double curr_asteroid_destruction_time;
    private String curr_order;
    private TaskForceCommand command;
    
    PhotonTorpedoProbe(int i, TaskForceCommand t){
        id = i;
        command = t;
        weapon_fire_rate = 3;
        weapon_damage = 5;
        curr_order = "";
    }
    
    public void run(){
        curr_order = command.giveOrder(id, curr_order);
        while(curr_order.equals("ACQUIRE_NEXT_TARGET")){
            System.out.println("Probe " + id + "\t\t->\tAcquiring Target");
            curr_asteroid = command.getTarget(id);
            curr_asteroid_impact_time = curr_asteroid.getTimeDiscovered() + curr_asteroid.getTimeToImpact();
            curr_asteroid_destruction_time = System.currentTimeMillis() + (curr_asteroid.getMass() / weapon_damage) * weapon_fire_rate;
            if(curr_asteroid_destruction_time < curr_asteroid_impact_time){
                curr_order = command.giveOrder(id, curr_order);
                if(curr_order.equals("RETURN_TO_BASE")) break;
                while(curr_asteroid.getMass() > 0){
                    System.out.println("Probe " + id + "\t\t->\tFiring at Asteroid " + curr_asteroid.getId());
                    curr_asteroid.setMass(curr_asteroid.getMass() - weapon_damage);
                    try{
                        sleep(weapon_fire_rate * 1000);
                    }catch(InterruptedException e){

                    }
                }
                System.out.println("Probe " + id + "\t\t->\tDestroyed Asteroid " + curr_asteroid.getId());
                command.targetDestroyed(id);
            }else{
                System.out.println("Probe " + id + "\t\t->\tSelf Destructing");
                command.probeSelfDestruct(id);
                break;
            }       
            curr_order = command.giveOrder(id, curr_order);
        }
    }
}
