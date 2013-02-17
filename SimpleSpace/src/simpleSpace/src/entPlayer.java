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
public class entPlayer extends entity {
        /*
     * class variables
     */
    ArrayList<item> inventory;
    double HPMax;
    
    entPlayer() {
        isAlive = true;
        HP = 2000.;
        HPMax = 2000.;
        name = "Player1";
        dmgModifier = 300;
        inventory = new ArrayList();
    }
    
    entPlayer(boolean alive, double hitpoints, String entName, double dmgM) {
        isAlive = alive;
        HP = hitpoints;
        HPMax = hitpoints;
        name = entName;
        dmgModifier = dmgM;
        inventory = new ArrayList();
    }
}
