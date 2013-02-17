/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleSpace.src;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author G89390
 */
public class entNPC extends entity {
    
    /*
     * class variables
     */
    String initSpam;
    
    entNPC() {
        isAlive = true;
        HP = 2000;
        name = "Porphyrian Battle Cruiser";
        dmgModifier = 150;
        gen = new Random();
        lootvalue = 200;
    }
    
    entNPC(boolean alive, double hitpoints, String entName, double dmgM, int lootv ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        gen = new Random();
        dmgModifier = dmgM;
        lootvalue = lootv;
    } 

}
