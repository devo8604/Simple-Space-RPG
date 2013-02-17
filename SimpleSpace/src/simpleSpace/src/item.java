
package simpleSpace.src;

import java.util.Scanner;

/**
 *
 * @author Devon Smith
 */
public class item {

    Scanner in = new Scanner(System.in);
    simpleSpace s = new simpleSpace();
    public static double rm = 1000; //Raw Material
    public static double m = 60;     //Missiles
    public static double r = 100;     //Railgun

    public void inventory() throws InterruptedException {

        System.out.println("|-----------------------------------|");
        Thread.sleep(250);
        System.out.println("|   Here is the ships inventory:    |");
        Thread.sleep(250);
        System.out.println("|   Raw Material: " + rm + "         |");
        Thread.sleep(250);
        System.out.println("|   Missile: " + m + "               |");
        Thread.sleep(250);
        System.out.println("|   Railgun: " + r + "               |");
        Thread.sleep(250);
        System.out.println("|-----------------------------------|");
        Thread.sleep(250);

        Thread.sleep(500);

        System.out.println("|--------------------------------------|");
        Thread.sleep(250);
        System.out.println("|   What do you want to do?            |");
        Thread.sleep(250);
        System.out.println("|   1. Repair Ship.                    |");
        Thread.sleep(250);
        System.out.println("|   2. Manufacture Missiles.           |");
        Thread.sleep(250);
        System.out.println("|   3. Manufacture Railgun Ammunition. |");
        Thread.sleep(250);
        System.out.println("|   4. Main Menu                       |");
        Thread.sleep(250);
        System.out.println("|--------------------------------------|");
        Thread.sleep(250);

        int menuItem = in.nextInt();

        switch (menuItem) {
            case 1:
                repair();
                inventory();
                break;
            case 2:
                missile();
                break;
            case 3:
                railgun();
                break;
            case 4:
                simpleSpace.mnMenu();
                break;
        }
    }

    public void repair() throws InterruptedException {
        System.out.println("You can repair your ship up to "
                + (simpleSpace.HP + rm));
        System.out.println("Please input a value from 1 to "
                + rm);
        int menuItem = in.nextInt();
        if (menuItem > 0 && menuItem <= rm) {
            rm -= menuItem;
            simpleSpace.HP += menuItem;
            System.out.println("Your is now at " + simpleSpace.HP);
        } else {
            System.out.println("We can't pull material out of vacuum.");
        }

    }

    public void missile() throws InterruptedException {
        double mi = rm / 4;

        System.out.println("|------------------------------------------|");
        Thread.sleep(250);
        System.out.println("|   You can create: " + mi +              "|");
        Thread.sleep(250);
        System.out.println("|   How many do you want to make?          |");
        Thread.sleep(250);
        System.out.println("|   Enter a number between 0 and " + mi + "|");
        Thread.sleep(250);
        System.out.println("|------------------------------------------|");
        Thread.sleep(250);
        int menuItem = in.nextInt();
        if (menuItem > 0 && menuItem <= mi) {
            rm -= (menuItem * 4);
            m += menuItem;
            System.out.println("You now have " + m + " Missiles.");
        } else {
            System.out.println("We can't pull material out of vacuum.");
        }
    }

    public void railgun() throws InterruptedException {
        double mi = rm / 2;

        System.out.println("|--------------------------------------------|");
        Thread.sleep(250);
        System.out.println("|   You can create: " + mi +                "|");
        Thread.sleep(250);
        System.out.println("|   How many do you want to make?            |");
        Thread.sleep(250);
        System.out.println("|   Enter a number between 0 and " + mi +   "|");
        Thread.sleep(250);
        System.out.println("|--------------------------------------------|");
        Thread.sleep(250);
        int menuItem = in.nextInt();
        if (menuItem > 0 && menuItem <= mi) {
            rm -= (menuItem * 2);
            r += menuItem;
            System.out.println("You now have " + m + " Missiles.");
        } else {
            System.out.println("We can't pull material out of vacuum.");
        }
    }
}
