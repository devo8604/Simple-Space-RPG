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
public class entNPC extends entity {
    
    /*
     * class variables
     */
    double damageMin, damageMax;
    String initSpam;
    double lewt[];
    Random gen;
    
    entNPC() {
        isAlive = true;
        HP = 2000;
        name = "Porphyrian Battle Cruiser";
        damageMin = 100;
        damageMax = 300;
        lewt[0] = 20;
        lewt[1] = 20;
        lewt[2] = 20;
    }
    
    entNPC(boolean alive, double hitpoints, String entName, double minDMG, double maxDMG, double loot[] ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        damageMin = minDMG;
        damageMax = maxDMG;
        lewt = loot;
    }
    
    public double fire() {
        return ((damageMax-damageMin) * gen.nextDouble()) + damageMin;
    }
    
    public void takeDamage(double damage) {
        HP -= damage;
        if (HP <= 0) isAlive = false;
        else isAlive = true;
    }
}
