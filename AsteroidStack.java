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
public class AsteroidStack implements AsteroidStorage{
    private Asteroid[] storage;
    private int num_items;
    private final int MAX_SIZE;
    
    AsteroidStack(int m){
        num_items = 0;
        MAX_SIZE = m;
        storage = new Asteroid[MAX_SIZE];
    }
    @Override
    public void insert(Asteroid a){
        if(num_items < MAX_SIZE){
            storage[num_items] = a;
            num_items++;
        }
    }
    @Override
    public Asteroid remove(){
        if(num_items > 0){
            num_items--;
            return storage[num_items];
        }
        return null;
    }
    @Override
    public int getSize(){
        return num_items;
    }
    @Override
    public Boolean isFull(){
        return num_items == MAX_SIZE;
    }
    @Override
    public Boolean isEmpty(){
        return num_items == 0;
    }

    
}


