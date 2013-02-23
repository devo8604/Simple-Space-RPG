
package SimpleSpace;

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
}
