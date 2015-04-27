/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidfield;

import static java.lang.Thread.sleep;

/**
 *
 * @author cory
 */
public class ProbeTest extends Thread implements Runnable{
    static PoissonDistribution pd = new PoissonDistribution();
    
    static int num_asteroids_to_destroy;
    static int num_tests = 1000000;            //one million tests

    int lowest_self_destructs = 10;
    int highest_self_destructs = 0;

    long min_self_destructs = 0;
    long max_self_destructs = 0;

    int num_tests_ran = 0;
    
    long total_size = 0;
    double total_impact_time = 0;
    
    public static void main(String[] args){
        num_asteroids_to_destroy = 55;
        num_tests = 1000000;
        
        Thread p_test = new ProbeTest();
        System.out.println("Running tests");
        p_test.start();
    }

    
    @Override
    public void run(){
        for(int test_count = 0; test_count < num_tests; test_count++){
            int min = 0;
            int max = 0;
            for(int i = 0; i < num_asteroids_to_destroy; i++){
                int d_status = nextProbeSelfDestructStatus();
                if(d_status == 0){ //neither asteroid can destroy, so both will have to self-destruct
                    min++;
                    max++;
                }else if(d_status == 1){
                    max++; //one can not destroy, so if it is the one that obtains the target it will have to self-destruct
                }
            }
            if(min < lowest_self_destructs) lowest_self_destructs = min;
            if(max > highest_self_destructs) highest_self_destructs = max;
            min_self_destructs += min;
            max_self_destructs += max;
            num_tests_ran++;
            try{sleep(0);}catch(InterruptedException e){}
        }
        System.out.println("Tests Complete!\n");
        long average_self_destructs = (max_self_destructs + min_self_destructs) / (2 * num_tests);
        int median_self_destructs = (lowest_self_destructs + highest_self_destructs) / 2;
        
        System.out.println("Size average: " + (total_size/(num_tests * 55)));
        System.out.println("Impact time average: " + (total_impact_time/(num_tests * 55)));
        
        System.out.println("Min number of self-destructs: " + lowest_self_destructs);
        System.out.println("Median number of self destructs: " + median_self_destructs);
        System.out.println("Max number of self-destructs: " + highest_self_destructs);
        
        System.out.println("Averge min number of self-destructs: " + (min_self_destructs / num_tests));
        System.out.println("Average number of self-destructs: " + average_self_destructs);
        System.out.println("Average max number of self-destructs: " + (max_self_destructs / num_tests));
        
        System.out.println("\nNumber of probes needed to ensure safe passage: " + highest_self_destructs);
    }
    
    public int nextProbeSelfDestructStatus(){
        double size, impact; //asteroid vars
        boolean destroyed_by_photon = false, destroyed_by_phaser = false; //asteroid destroyed vars
        
        size = pd.nextSize();
        impact = pd.nextImpact();
        
        total_size += size;
        total_impact_time += impact;
        
        if(timeToDestroyWithPhotonTorpedo(size) < impact) destroyed_by_photon = true;
        if(timeToDestroyWithPhaser(size) < impact) destroyed_by_phaser = true;
        
        if(destroyed_by_photon && destroyed_by_phaser) return 2;
        if(destroyed_by_photon || destroyed_by_phaser) return 1;
        return 0;
    }
    
    public double timeToDestroyWithPhotonTorpedo(double size){
        return (Math.ceil(size / 5) - 1 ) * 3;
    }
    
    public double timeToDestroyWithPhaser(double size){
        return (Math.ceil(size / 3) - 1 ) * 2;
    }
    
    
}
