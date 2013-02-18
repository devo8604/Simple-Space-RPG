/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author G89390
 */
public class entity {
    /*
     * class primary variables
     */
    public boolean isAlive;
    public double HP, dmgModifier;
    public int lootvalue, lootrolls;
    public String name;
    public Random gen;
    
    entity() {
        isAlive = true;
        HP = 1;
        name = "<NAME!>";
        gen = new Random();
        lootrolls = 1;
    }
    
    entity(boolean alv, double hp, String nm, String is) {
        isAlive = alv;
        HP = hp;
        name = nm;
        gen = new Random();
        lootrolls = 1;
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
        if (HP <= 0) isAlive = false;
        else isAlive = true;
    }
    
    public void battle(entity target) {
        takeDamage(target.dealDamage());
        target.takeDamage(dealDamage());
    }
    
    public void loot( ArrayList<item> inventory) {
        for(;lootrolls > 0; lootrolls--) {
            int choice = gen.nextInt(inventory.size() - 1);
            inventory.get(choice).qty += lootvalue;
            System.out.println("Looting " + name + " yielded " + lootvalue + " " + inventory.get(choice).title + " (Total = " + inventory.get(choice).qty + ")");
        }
    }
}
