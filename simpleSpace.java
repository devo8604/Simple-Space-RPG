package SimpleSpace.src;

//~--- JDK imports ------------------------------------------------------------

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Devon Smith
 */
import java.util.*;

public class simpleSpace {

    public static double HP = 3000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("In a Galaxy far far far away in the distant future,\n"
                + "you are the Captain of an alliance battleship, trapped deep\n"
                + "within enemy territory with Intelligence information that could\n"
                + "finally put an end to this war. In order to make it back to Alliance\n"
                + "space, you must successfully make 15 FTL jumps. That is 15 times\n"
                + "that the enemy has the oppurtunity to stop you, and turn the tide\n"
                + "of the war in their favor...");
        mnMenu();
    }

    public static void mnMenu() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        event ev = new event();
        item item = new item();

        for (int i = 0; i < 15; i++) {
            while (HP > 0) {
                System.out.println("|----------------|");
                System.out.println("|  Select one:   |");
                System.out.println("|  1. Jump.      |");
                System.out.println("|  2. Inventory  |");
                System.out.println("|  3. Quit.      |");
                System.out.println("|----------------|");

                int menuItem = in.nextInt();

                switch (menuItem) {
                    case 1:
                        double battleChoice = ev.ranNum();

                        if (battleChoice > 80) {
                            eneFrigate fr = new eneFrigate();

                            fr.battleStart();
                            Thread.sleep(250);
                            fr.battle();
                            mnMenu();
                        } else if (battleChoice > 40) {
                            eneBattleship bs = new eneBattleship();

                            bs.battleStart();
                            Thread.sleep(250);
                            bs.battle();
                            mnMenu();
                        } else {
                            eneCruiser cr = new eneCruiser();

                            cr.battleStart();
                            Thread.sleep(250);
                            cr.battle();
                            mnMenu();
                        }
                    case 2:
                        item.inventory();

                        break;

                    case 3:
                        System.exit(0);

                        break;
                }
            }

            System.out.println("You have failed. GAME OVER");
            System.exit(0);
        }

        System.out.println("Congratulations Captain, you have successfully brought\n"
                + "your crew home safely, for the most part, and delivered your precious\n"
                + "cargo to the alliance fleet headquarters. The war will soon be over because\n"
                + "of you.");
        System.exit(0);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
