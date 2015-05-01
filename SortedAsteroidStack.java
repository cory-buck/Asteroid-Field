/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroidfield;

/**
 *
 * @author cBeezy
 */
public class SortedAsteroidStack implements AsteroidStorage{
    final private Asteroid[] storage;
    private int num_items;
    
    public SortedAsteroidStack(int MAX){
        storage = new Asteroid[MAX];
        num_items = 0;
    }
    
    @Override
    public void insert(Asteroid a){       
        Asteroid temp = a;
        Asteroid temp2;
        for(int i = 0; i < num_items; i++){
            temp2 = storage[i];
            if(storage[i] != null){
                if(temp2.getTimeOfImpact() < temp.getTimeOfImpact()){
                    storage[i] = temp;
                    temp = temp2;
                }
            }
        }
        storage[num_items] = temp;
        num_items++;
    }    

    @Override
    public Asteroid remove(){
        num_items--;
        return storage[num_items];
    }
    
    @Override
    public int getSize(){
        return num_items;
    }
    
    @Override
    public Boolean isFull(){
        return num_items == storage.length;
    }
    
    @Override
    public Boolean isEmpty(){
        return num_items == 0;
    }
    
}
