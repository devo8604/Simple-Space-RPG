package SimpleSpaceTwo;

import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @version 0.1.0
 * @author devonsmith
 */
public class SimpleSpaceTwo {

    /*
     * Class variable declarations
     */
    public static int gameJumpCTR; //default 1
    public static int gameJumpMAX; //deault 15
    public static ArrayList<entity> encounters;
    public static ArrayList<item> shipItems;
    public static entity player;
    public Random gen = new Random();

    public static void main(String[] args) {

        simpleSpace() {
            //Player Cotr: (isAlive, HP, Name, Damage Modifier)
            player = new entity(true, 3000, "Planet Express", 2);
            gameJumpCTR = 0;
            gameJumpMAX = 15;
            System.out.println("In a Galaxy far far far away in the distant future,\n"
                    + "you are the Captain of an alliance battleship, trapped deep\n"
                    + "within enemy territory with Intelligence information that could\n"
                    + "finally put an end to this war. In order to make it back to Alliance\n"
                    + "space, you must successfully make " + gameJumpMAX + " FTL jumps. That is "
                    + gameJumpMAX + " times\n"
                    + "that the enemy has the oppurtunity to stop you, and turn the tide\n"
                    + "of the war in their favor...");
            encounters = new ArrayList();
            for(int i=0; i<gameJumpMAX; i++) {
                encounters.add(new entity());
                
            //NPC cotr: (isAlive, HP, Name, Damage Modifier, Loot Value, Loot Rolls)
                encounters.add(new entity(true, 3000, "Battleship", 3, 3, 1));
                encounters.add(new entity(true, 4000, "Frigate", 4, 4, 2));
                encounters.add(new entity(true, 2000, "Gunship", 2, 2, 1));
            
            //Items: (Item ID, Quantity, Name, Description, repair, damage)
                shipItems = new ArrayList<>();
                shipItems.add(new item(0, 100, "Missile", "This is a self-propelled seeking projectile. It has a high damage value.", 0, 300));
                shipItems.add(new item(1, 1000, "Rail Slug", "This is a rail-propelled unguided projectile. It has a med-high damage value.", 0, 150));
                shipItems.add(new item(2, 20, "Repair Drone", "This is a self-deploying repair Drone. Each unit repairs 100 HP of damage.", 100, 0));
                entity.inventory = shipItems;
            }
        }

    }
}
