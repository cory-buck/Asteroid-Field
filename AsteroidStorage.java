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
public interface AsteroidStorage {
    public void insert(Asteroid a);
    public Asteroid remove();
    public int getSize();
    public Boolean isFull();
    public Boolean isEmpty();
}
