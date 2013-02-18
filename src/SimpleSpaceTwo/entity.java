/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpaceTwo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author devonsmith
 */
public class entity {
    /*
     * class primary variables
     */

    public boolean isAlive;
    public double HP, dmgModifier;
    public int lootvalue, lootrolls;
    public String name, initSpam;
    public Random gen = new Random();
    public static ArrayList<item> inventory;
    double HPMax;

    
    //NPC Method
    entity(boolean alive, double hitpoints, String entName, double dmgM, int lootv, int lootr) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        dmgModifier = dmgM;
        lootvalue = lootv;
        lootrolls = lootr;
    }
    //Player Method
    entity(boolean alive, double hitpoints, String entName, double dmgM) {
        isAlive = alive;
        HP = hitpoints;
        name = entName; //make this to where player can input ship name.
        dmgModifier = dmgM;
        inventory = new ArrayList();
    }

    public double dealDamage() {
        double result = dmgModifier * gen.nextDouble();
        System.out.println(name + " fired for " + result + " damage!");
        return result;
    }

    public void takeDamage(double damage) {
        System.out.println(name + " took " + damage + " damage!");
        HP -= damage;
        System.out.println(name + "'s health: " + HP);
        if (HP <= 0) {
            isAlive = false;
        } else {
            isAlive = true;
        }
    }

    public void battle(entity target) {
        takeDamage(target.dealDamage());
        target.takeDamage(dealDamage());
    }

    public void loot(ArrayList<item> inventory) {
        for (; lootrolls > 0; lootrolls--) {
            int choice = gen.nextInt(inventory.size() - 1);
            inventory.get(choice).qty += lootvalue;
            System.out.println("Looting " + name + " yielded " + lootvalue + " "
                    + inventory.get(choice).title + " (Total = "
                    + inventory.get(choice).qty + ")");
        }
    }
}
