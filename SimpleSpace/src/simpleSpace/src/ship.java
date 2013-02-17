
package simpleSpace.src;

/**
 *
 * @author Devon Smith
 */
public class ship {

    event event = new event();

    public void battleFire() {
        double fire = event.attack();
        System.out.print("You have taken " + fire + " damage!");
        //simpleSpace.HP -= fire;
        //System.out.println("      You have " + simpleSpace.HP + " HP left!");
    }
}
