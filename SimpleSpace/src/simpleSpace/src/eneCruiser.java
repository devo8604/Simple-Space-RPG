
package simpleSpace.src;

/**
 *
 * @author Devon Smith
 */
public class eneCruiser extends ship {

    double eHP = 2000;
    double blast;
    int barrage;

    public void battleStart() {

        System.out.println("Enemy Cruiser detected!" + "\n" + "Enemy HP at: " + eHP);
    }

    public void battle() throws InterruptedException {
        event.eneBattle(eHP);
        item.rm += 750;
        System.out.println("You have recover some Raw Material.");
    }
}
