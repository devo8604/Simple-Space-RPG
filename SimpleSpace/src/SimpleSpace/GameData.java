
package SimpleSpace;

import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author ngschmidt
 */
public class GameData extends initGameData {
    
    public GameData(){
        super();
    }

    public void inventoryMenu(Scanner in) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
 
        simpleSpace simple = new simpleSpace();
        
        System.out.println("|--------------------|");
        System.out.println("|  Select one:       |");
        System.out.println("|  1. List inventory |");
        System.out.println("|  2. Repair         |");
        System.out.println("|  3. **reserved**   |");
        System.out.println("|  4. Main Menu      |");
        System.out.println("|--------------------|");

        //boolean running = true;
        //while(running) {
            int menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println(listInv());  //lists player inventory
                    break;
                case 2:
                    repair();
                    break;
                case 3:
                    //future use
                case 4:
                    //running = false;
                    simple.startGameEvent(in);
                    break;
                default:
                    System.out.println("Please try again.");
                    break;
            }
        //}

    }
    
    public String listInv() {
        String describeItem = new String();
        for (int i = 0; i < possibleItems.size(); i++) {
            describeItem += "Item: " + possibleItems.get(i).title + " desc: " 
                    + possibleItems.get(i).desc + " qty: " + possibleItems.get(i).qty 
                    + " Repair Val: " + possibleItems.get(i).repairValue
                    + " Damage Val " + possibleItems.get(i).dmgValue + "\n";
        }
        return describeItem;
    } 
    public void repair() {
        Scanner in = new Scanner(System.in);
        initGameData data = new initGameData();
        System.out.println("\nYour current HP is at " + data.plyrs.get(0).HP);
        System.out.println("You have " + data.possibleItems.get(2).qty + " Repair Drones.\n" 
                + "and each have a repair value of " + data.possibleItems.get(2).repairValue + " HP."
                + "\nHow many do you want to use?");
        int menuItem = in.nextInt();
        if (data.possibleItems.get(2).qty >= menuItem) {
            data.plyrs.get(0).HP += (data.possibleItems.get(2).repairValue * menuItem);
            System.out.println("You have " + data.plyrs.get(0).HP + " HP");
        } else {
            System.out.println("You do not have enough drones!");
        }
    }
}