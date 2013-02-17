/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleSpace.src;

/**
 *
 * @author Devon Smith
 */
public class eneBattleship extends ship {

    double eHP = 3000;
    double blast;
    int barrage;

    public void battleStart() {

        System.out.println("Enemy Battleship detected!" + "\n" + "Enemy HP at: " + eHP);
    }

    public void battle() throws InterruptedException {
        event.eneBattle(eHP);
        item.rm += 1000;
        System.out.println("You have recover some Raw Material.");
    }
}
