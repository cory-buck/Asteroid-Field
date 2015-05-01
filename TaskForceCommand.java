/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfield;

/**
 *
 * @author cory
 */
public class TaskForceCommand extends Thread{
    private AsteroidSimulationGUI gui;
    private SortedAsteroidStack asteroids;
    private int max_shield_hits;
    private int num_shield_hits;
    private int num_asteroids_destroyed;
    private int num_asteroids_left;
    private int num_probes;
    private double last_time_update;
    private Boolean destroyed;
    private Boolean mission_complete;
    private Thread[] probes;
    
    @Override
    public void run(){
        while(!destroyed && !mission_complete){
            try{sleep(0);}catch(Exception e){}
            gui.setTime(System.currentTimeMillis());
        }
        if(destroyed)gui.setStatus("FLEET DESTROYED");
        else gui.setStatus("SUCCESS");
    }
    
    public void Init(int MAX_STORAGE, int SHIELD_DEFENSE, int ASTEROIDS_TO_DESTROY, int PROBE_COUNT){
        gui = new AsteroidSimulationGUI(SHIELD_DEFENSE,ASTEROIDS_TO_DESTROY, PROBE_COUNT, MAX_STORAGE);
        gui.setStatus("INITIALIZING");
        
        asteroids = new SortedAsteroidStack(MAX_STORAGE);
        max_shield_hits = SHIELD_DEFENSE;
        num_shield_hits = 0;
        num_asteroids_destroyed = 0;
        num_asteroids_left = ASTEROIDS_TO_DESTROY;
        probes = new Thread[PROBE_COUNT];
        num_probes = PROBE_COUNT;
        last_time_update = 0.0;
        destroyed = false;  
        mission_complete = false;
        gui.setNumAsteroids(num_asteroids_left);
        
        
        for(int i = 0; i < num_probes; i++){
            switch(i){
                case 0:
                    probes[i] = new ScoutProbe(i,this);
                    break;
                case 1:
                    probes[i] = new PhotonTorpedoProbe(i,this);
                    break;
                case 2:
                    probes[i] = new PhotonTorpedoProbe(i,this);
                    break;
                default:
                    probes[i] = new PhaserProbe(i,this);
                    break;                
            }
            probes[i].start();
        }
        
        try{Thread.sleep(2000);}
        catch(InterruptedException e){}
        
        gui.setStatus("RUNNING");
        
        System.out.println("Command\t\t->\tInitialized");
    }
    
    public synchronized void reportAsteroid(Asteroid a){
        gui.setProbeStatus(0, "REPORTING_TARGET");
        if(asteroids.isFull()){
            System.out.println("Command\t\t->\tScout, Asteroid Was Not Accepted");
            System.out.println("Command\t\t->\tFleet Incurred One Hit");
            num_shield_hits++;
            num_asteroids_left--;
            gui.setShields(max_shield_hits - num_shield_hits);
            if(num_shield_hits == max_shield_hits){
                gui.setStatus("SIMULATION FAILED");
                destroyed = true;
            }
        }else{
            if(num_asteroids_left <= asteroids.getSize()) gui.setProbeStatus(0, "RETURNING_TO_BASE");
            else{
                asteroids.insert(a);
                gui.setStorage(asteroids.getSize());
                System.out.println("Command\t\t->\tScout, Asteroid Accepted");
            }
        }
        notify();
        gui.setNumAsteroids(num_asteroids_left);
    }
    
    public synchronized Asteroid getTarget(int i){
        gui.setProbeStatus(i, "ACQUIRING_TARGET");
        while(asteroids.getSize() == 0){
            if(mission_complete || destroyed){
                notify();
                return null;
            }
                try{wait();}
                catch(InterruptedException e){}
        }
        notify();
        gui.setProbeStatus(i, "DESTROYING_TARGET");
        gui.setStorage(asteroids.getSize() - 1);
        return asteroids.remove();
    }
    
    public void targetDestroyed(int i){
       num_asteroids_left--;
       num_asteroids_destroyed++;
       gui.setNumAsteroids(num_asteroids_left);
       if(num_asteroids_left <= 0)mission_complete = true;
    }
    
    public void probeSelfDestruct(int i){
        num_probes--;
        num_asteroids_left--;
        num_asteroids_destroyed++;
        gui.setNumAsteroids(num_asteroids_left);
        gui.setProbeStatus(i, "DESTROYED");
    }
    
    public String giveOrder(int i, String curr_order){
        
            if(destroyed || mission_complete){
                gui.setProbeStatus(i, "RETURNING_TO_BASE");
                return "RETURN_TO_BASE";
            }else{
                if(i == 0){
                    if(curr_order.equals("ACQUIRE_NEXT_TARGET")){
                        if(destroyed || mission_complete){
                            gui.setProbeStatus(i, "RETURNING_TO_BASE");
                            return "RETURN_TO_BASE";
                        }
                        gui.setProbeStatus(i, "REPORTING_TARGET");
                        return "REPORT_TARGET";
                    }else{
                        gui.setProbeStatus(i, "ACQUIRING_TARGET");
                        return "ACQUIRE_NEXT_TARGET";
                    }
                }else{
                      if(curr_order.equals("ACQUIRE_NEXT_TARGET")){
                          if(destroyed || mission_complete){
                              gui.setProbeStatus(i, "RETURNING_TO_BASE");
                              return "RETURN_TO_BASE";
                          }
                          gui.setProbeStatus(i, "DESTROYING_TARGET");
                          return "DESTROY_TARGET";
                      }else{
                          gui.setProbeStatus(i, "ACQUIRING_TARGET");
                          return "ACQUIRE_NEXT_TARGET";
                      }
                }
            }           

    }
}
