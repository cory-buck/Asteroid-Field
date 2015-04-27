/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroidfieldsimulation;

/**
 *
 * @author cBeezy
 */
public class AsteroidQueue implements AsteroidStorage{
    private Asteroid[] storage;
    private int num_items;
    private int front;
    private int back;
    
    public AsteroidQueue(int MAX){
        storage = new Asteroid[MAX];
        num_items = 0;
        front = 0;
        back  = 0;
    }
    
    @Override
    public void insert(Asteroid a){       
        num_items++;
        storage[back] = a;
        back = (back + 1) % storage.length;
    }    

    @Override
    public Asteroid remove(){
        num_items--;
        Asteroid temp = storage[front];
        front = (front + 1) % storage.length;
        return temp;
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
