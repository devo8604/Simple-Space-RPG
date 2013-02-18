/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

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
        lootrolls = 1;
        initSpam = "(V)...(-,,,-)...(Y) A crab like ship approaches!";
    }
    
    entNPC(boolean alive, double hitpoints, String entName, double dmgM, int lootv ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        gen = new Random();
        dmgModifier = dmgM;
        lootvalue = lootv;
        lootrolls = 1;
    } 
    
    entNPC(boolean alive, double hitpoints, String entName, double dmgM, int lootv, int lootrolls ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        gen = new Random();
        dmgModifier = dmgM;
        lootvalue = lootv;
    } 
}
