/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleSpace.src;

/**
 *
 * @author G89390
 */
public class entity {
    /*
     * class primary variables
     */
    public boolean isAlive;
    public double HP;
    public String name;
    
    entity() {
        isAlive = true;
        HP = 1;
        name = "<NAME!>";
    }
    
    entity(boolean alv, double hp, String nm, String is) {
        isAlive = alv;
        HP = hp;
        name = nm;
    }
}
