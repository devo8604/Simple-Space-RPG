/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author g96930
 */
public class inventory {

    public ArrayList<item> possibleItems;
    String describeItem;

    public String listInv() {
        for (int i = 0; i <= possibleItems.size(); i++) {
            describeItem = possibleItems.get(i).title + " " + possibleItems.get(i).desc
                    + " " + possibleItems.get(i).qty + " " + possibleItems.get(i).repairValue
                    + " " + possibleItems.get(i).dmgValue;
            return describeItem;
        }
        return null;

    }
}
