/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleSpace.src;

import java.util.Random;

/**
 *
 * @author G89390
 */
public class entPlayer extends entity {
        /*
     * class variables
     */
    Random gen;
    item[] inventory;
    double HPMax;
    
    entPlayer() {
        isAlive = true;
        HP = 2000.;
        HPMax = 2000.;
        name = "Player1";
    }
    
    entPlayer(boolean alive, double hitpoints, String entName) {
        isAlive = alive;
        HP = hitpoints;
        HPMax = hitpoints;
        name = entName;
    }
    
    public double fire() {
        return 1.;
    }
    
    public void takeDamage(double damage) {
        HP -= damage;
        if (HP <= 0) isAlive = false;
        else isAlive = true;
    }
}
