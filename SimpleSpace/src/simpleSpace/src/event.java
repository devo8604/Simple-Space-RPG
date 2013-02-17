
package simpleSpace.src;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Devon Smith
 */
public class event {

    int missile = 300; //Damage
    int railGun = 150; //Damage  
    Random gen = new Random();
    Scanner in = new Scanner(System.in);

    public double ranNum() {

        double d;

        int r = gen.nextInt(101);
        if (r > 75) {
            d = 1;
            return d;
        } else if (r > 50) {
            d = 0.50;
            return d;
        } else if (r > 25) {
            d = 0.25;
            return d;
        } else {
            d = 0;
            return d;
        }
    }
/*
    //Missile attack
    public double missile(int barrage) {

        if (barrage <= item.m && barrage > 0) {
            double d = ranNum();
            double mDamage = (missile * d) * barrage;
            item.m -= barrage;
            return mDamage;
        } else if (barrage == -6) {
            double d = ranNum();
            double mDamage = (missile * d) * 6;
            return mDamage;
        } else {
            System.out.println("You are out of Missiles, firing Railgun instead...");
            railGun(0);
        }
        return 0;
    }

    //Railgun attack
    public double railGun(int barrage) {

        if (barrage <= item.r && barrage > 0) {
            double d = ranNum();
            double rDamage = (railGun * d) * barrage;
            item.r -= barrage;
            return rDamage;
        } else if (barrage == -6) {
            double d = ranNum();
            double rDamage = (railGun * d) * 6;
            return rDamage;
        } else {
            System.out.println("You are out of Railgun ammo, firing Missiles instead...");
            missile(0);
        }
        return 0;
    }

    //Enemy attack method
    public double attack() {
        double missile1 = missile(-6);
        double railgun1 = railGun(-6);
        double deal = gen.nextInt(101);


        System.out.println(deal);
        if (deal > 70 && missile1 != 0) {
            System.out.println("You have been hit by a missile barrage!");
            return missile1 * 2;
        } else if (deal > 20 && railgun1 != 0) {
            System.out.println("You have been hit by their railguns!");
            return railgun1;
        } else {
            System.out.println("That was a near miss!");
            return 0;
        }
    }
    public void eneBattle(double eHP) throws InterruptedException {


        while (eHP > 0 && simpleSpace.HP > 0) {

            System.out.println("Select one:        |");
            Thread.sleep(250);
            System.out.println("1. Fire Missiles.  |");
            Thread.sleep(250);
            System.out.println("2. Fire Railgun.   |");
            Thread.sleep(250);

            int weaponSel = in.nextInt();
            double blast;

            new ship().battleFire();

            switch (weaponSel) {
                case 1:

                    System.out.println("How many do you want to fire?");
                    System.out.println("Enter a number between 1 and " + item.m);
                    int barrage = in.nextInt();
                    blast = missile(barrage);
                    if (blast == 0) {
                        System.out.println("You missed!");
                    } else {
                        eHP -= blast;
                        System.out.println("Enemy ship has taken " + blast + " damage!" + "\n"
                                + "HP remaining: " + eHP);
                    }
                    break;

                case 2:

                    System.out.println("How many do you want to fire?");
                    System.out.println("Enter a number between 1 and " + item.r);
                    barrage = in.nextInt();

                    blast = railGun(barrage);
                    if (blast == 0) {
                        System.out.println("You missed!");
                    } else {
                        eHP -= blast;
                        System.out.println("Enemy ship has taken " + blast + " damage!" + "\n"
                                + "HP remaining: " + eHP);
                    }
                    break;
                //SUPERNOVA!!! case 0:
                    eHP -= 9999999;
                    System.out.println("I think we can call that a victory cause you just blew up the solar system.");
                    simpleSpace.mnMenu();
                    break;
            }
        }


    }
*/
    /*
     public void save() throws IOException {
     double pHP = simpleSpace.HP; 
    
     FileOutputStream saveFile = new FileOutputStream("SimpleSpace.sav");
     ObjectOutputStream save = new ObjectOutputStream(saveFile);
    
     save.writeObject(pHP);
    
     save.close();
     System.out.println("Game Saved!");
     return;
     }
     public void load() throws FileNotFoundException, IOException, ClassNotFoundException {

    
     FileInputStream saveFile = new FileInputStream("SimpleSpace.sav");
     ObjectInputStream save = new ObjectInputStream(saveFile);
    
     simpleSpace.HP = (double) save.readObject();
    
     save.close();
     System.out.println("Game Loaded");
     return;
     } 
     */
}
