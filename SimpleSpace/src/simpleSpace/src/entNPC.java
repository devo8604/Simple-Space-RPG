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
    Random gen;
    
    entNPC() {
        isAlive = true;
        HP = 2000;
        name = "Porphyrian Battle Cruiser";
        damageMin = 100;
        damageMax = 300;
        gen = new Random();
    }
    
    entNPC(boolean alive, double hitpoints, String entName, double minDMG, double maxDMG ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        damageMin = minDMG;
        damageMax = maxDMG;
        gen = new Random();
    }
    
    public double fire() {
        double result = ((damageMax-damageMin) * gen.nextDouble()) + damageMin;
        System.out.println(name + " fired for " + result + " damage!");
        return result;
    }
    
    public void takeDamage(double damage) {
        System.out.println(name + " took " + damage + "damage!");
        HP -= damage;
        System.out.println(name + "'s health: " + HP);
        if (HP <= 0) isAlive = false;
        else isAlive = true;
    }
}
