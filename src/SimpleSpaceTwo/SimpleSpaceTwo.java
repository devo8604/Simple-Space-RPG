package SimpleSpaceTwo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    SimpleSpaceTwo() {
        //Player Cotr: (isAlive, HP, Name, Damage Modifier)
        player = new entity(true, 3000, "Planet Express", 999999);
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

        for (int i = 0; i < gameJumpMAX; i++) {

            //NPC cotr: (isAlive, HP, Name, Damage Modifier, Loot Value, Loot Rolls)
            encounters = new ArrayList();

            encounters.add(new entity(true, 3000, "Battleship", 50, 3, 1));
            encounters.add(new entity(true, 4000, "Frigate", 60, 4, 2));
            encounters.add(new entity(true, 2000, "Gunship", 40, 2, 1));

            //Items cotr: (Item ID, Quantity, Name, Description, repair, damage)
            shipItems = new ArrayList<>();
            int itemSize = shipItems.size();
            shipItems.add(new item(0, 100, "Missile", "This is a self-propelled seeking projectile. It has a high damage value.", 0, 300));
            shipItems.add(new item(1, 1000, "Rail Slug", "This is a rail-propelled unguided projectile. It has a med-high damage value.", 0, 150));
            shipItems.add(new item(2, 20, "Repair Drone", "This is a self-deploying repair Drone. Each unit repairs 100 HP of damage.", 100, 0));
            entity.inventory = shipItems;
        }
    }

    public void startGameEvent(Scanner in) throws InterruptedException {
        while (player.isAlive) {
            if (player.isAlive) {
                System.out.println("|----------------|");
                System.out.println("|  Select one:   |");
                System.out.println("|  1. Jump.      |");
                System.out.println("|  2. Inventory  |");
                System.out.println("|  3. Quit.      |");
                System.out.println("|----------------|");

                int menuItem = in.nextInt();

                switch (menuItem) {
                    case 1:
                        gameJumpCTR++;
                        int encSize = encounters.size();
                        entity instanceEnc = encounters.get(gen.nextInt(encSize));
                        while (instanceEnc.isAlive) {
                            Thread.sleep(250);
                            player.battle(instanceEnc);
                        }
                        if (!instanceEnc.isAlive && player.isAlive) {
                            System.out.println("Success! You vanquished " + instanceEnc.name + "! \n And now for the loot!");
                            instanceEnc.loot(entity.inventory);
                        } else {
                            startGameFailEvent();
                        }
                    case 2:

                        break;

                    case 3:
                        System.exit(0);

                        break;
                }
            } else {
                startGameFailEvent();
            }
        }
    }

    public void startGameSuccessEvent() {
        System.out.println("Congratulations Captain, you have successfully brought\n"
                + "your crew home safely, for the most part, and delivered your precious\n"
                + "cargo to the alliance fleet headquarters. The war will soon be over because\n"
                + "of you.");
        System.exit(0);
    }

    public static void startGameFailEvent() {
        System.out.println("You have failed. GAME OVER");
        System.exit(0);
    }

    public void mnMenu() throws InterruptedException {
        Scanner in = new Scanner(System.in);

        for (; gameJumpCTR < gameJumpMAX; gameJumpCTR++) {
            startGameEvent(in);
        }
        if (player.isAlive) {
            startGameSuccessEvent();
        } else {
            startGameFailEvent();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleSpaceTwo game = new SimpleSpaceTwo();
        game.mnMenu();
    }
}
