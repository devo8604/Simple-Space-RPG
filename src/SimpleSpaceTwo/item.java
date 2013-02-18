/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpaceTwo;

/**
 *
 * @author Devon Smith
 */
public class item {

    int itemID, qty;
    String title, desc;
    double repairValue, dmgValue;

    item(int id, int quantity, String name, String description, double repair, double damage) {
        itemID = id;
        qty = quantity;
        title = name;
        desc = description;
        repairValue = repair;
        dmgValue = damage;
    }

    public boolean isEmpty() {
        if (qty <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
