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
    public String name, initSpam;
    public Random gen = new Random();
    ArrayList<item> inventory = new ArrayList();
    double HPMax;
    
    entity() {
        isAlive = true;
        HP = 2000;
        name = "Porphyrian Battle Cruiser";
        dmgModifier = 150;
        lootvalue = 200;
        lootrolls = 1;
        initSpam = "(V)...(-,,,-)...(Y) A crab like ship approaches!";
    }
    
    entity(entity a) {
        this.isAlive = a.isAlive;
        this.HP = a.HP;
        this.dmgModifier = a.dmgModifier;
        this.lootvalue = a.lootvalue;
        this.lootrolls = a.lootrolls;
        this.name = a.name;
        this.initSpam = a.initSpam;
        this.gen = a.gen;
        this.inventory = a.inventory;
        this.HPMax = a.HPMax;
    }
    
    entity(boolean alv, double hp, String nm, String is) {
        isAlive = alv;
        HP = hp;
        name = nm;
        lootrolls = 1;
    }
    
    entity(boolean alive, double hitpoints, String entName, double dmgM, int lootv ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        dmgModifier = dmgM;
        lootvalue = lootv;
        lootrolls = 1;
    } 
    
    entity(boolean alive, double hitpoints, String entName, double dmgM, int lootv, int lootrolls ) {
        isAlive = alive;
        HP = hitpoints;
        name = entName;
        dmgModifier = dmgM;
        lootvalue = lootv;
    }
    
    entity(boolean alive, double hitpoints, String entName, double dmgM) {
        isAlive = alive;
        HP = hitpoints;
        HPMax = hitpoints;
        name = entName;
        dmgModifier = dmgM;
    }
    
    public double dealDamage() {
        double result = dmgModifier * gen.nextDouble();
        return result;
    }
    
    public void takeDamage(double damage) {
        HP -= damage;
        if (HP <= 0) isAlive = false;
        else isAlive = true;
    }
    
    public void loot(entity target) {
        int choice = gen.nextInt(inventory.size());
        for(;target.lootrolls > 0; target.lootrolls--) {
            inventory.get(choice).qty += target.lootvalue;
            System.out.println("Looting " + target.name + " yielded " + target.lootvalue + " " + inventory.get(choice).title + " (Total = " + inventory.get(choice).qty + ")");
        }
    }
}
