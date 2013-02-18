/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpaceTwo;

import java.util.Random;

/**
 *
 * @author Devon Smith
 */
public class event {

    int missile = 300; //Damage
    int railGun = 150; //Damage  
    Random gen = new Random();

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
}
