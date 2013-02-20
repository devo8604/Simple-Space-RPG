package SimpleSpace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Devon Smith
 */
public class inventory {
    
 
    public ArrayList<item> possibleItems;
    String describeItem;

    public void inventoryMenu(Scanner in) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        simpleSpace simple = new simpleSpace();
        
        System.out.println("|--------------------|");
        System.out.println("|  Select one:       |");
        System.out.println("|  1. List inventory |");
        System.out.println("|  2. **reserved**   |");
        System.out.println("|  3. **reserved**   |");
        System.out.println("|  4. Main Menu      |");
        System.out.println("|--------------------|");
        
        int menuItem = in.nextInt();
        switch(menuItem) {
            case 1:
                listInv();  //lists player inventory
              break;
            case 2:
                //future use
              break;
            case 3:
                //future use
            case 4:
                simple.startGameEvent(in);
        }
        
    }

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
