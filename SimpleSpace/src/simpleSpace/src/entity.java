/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleSpace.src;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author G89390
 */
public class entity {
    /*
     * class primary variables
     */
    public boolean isAlive, looted;
    public double HP, dmgModifier;
    public int lootvalue;
    public String name;
    public Random gen;
    
    entity() {
        isAlive = true;
        HP = 1;
        name = "<NAME!>";
        gen = new Random();
    }
    
    entity(boolean alv, double hp, String nm, String is) {
        isAlive = alv;
        HP = hp;
        name = nm;
        gen = new Random();
    }
    
    public double dealDamage() {
        double result = dmgModifier * gen.nextDouble();
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
    
    public void battle(entity target) {
        takeDamage(target.dealDamage());
        target.takeDamage(dealDamage());
    }
    
    public item loot( ArrayList<item> possibilities) {
        if(!looted) {
            item chosenLoot = possibilities.get(gen.nextInt(possibilities.size() - 1));
            chosenLoot.qty = lootvalue;
            System.out.println("Looting " + name + " yielded " + chosenLoot.qty + " " + chosenLoot.title);
            looted = true;
            return chosenLoot;
        }
        else return new item(0,0,"","");
    }
}
