
package SimpleSpace;

/**
 *
 * @author Devon Smith
 */
public class item {

    int itemID, qty;
    String title, desc;
    double repairValue, dmgValue;
    
    item() {
        itemID = 0;
        qty = 1;
        title = "Not a real Item";
        desc = "This isn't a real item. look at something else!";
    }
    
    item(int id, int quantity, String name, String description) {
        itemID = id;
        qty = quantity;
        title = name;
        desc = description;
        repairValue = 0.;
        dmgValue = 0.;
    }
    item(int id, int quantity, String name, String description, double repair, double damage) {
        itemID = id;
        qty = quantity;
        title = name;
        desc = description;
        repairValue = repair;
        dmgValue = damage;
    }
    
    public boolean isEmpty() {
        if (qty <= 0) return true;
        else return false;
    }
    
    public void use(entity target) {
        target.HP -= dmgValue;
        if (target.HP + repairValue <= target.HPMax) {
            target.HP += repairValue;
        } else {
            target.HP = target.HPMax;
        }
    }

}
