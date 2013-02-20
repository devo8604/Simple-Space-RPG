
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

    public void inventoryMenu(Scanner in) throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        System.out.println("|--------------------|");
        System.out.println("|  Select one:       |");
        System.out.println("|  1. List inventory |");
        System.out.println("|  2. **reserved**   |");
        System.out.println("|  3. **reserved**   |");
        System.out.println("|  4. Main Menu      |");
        System.out.println("|--------------------|");

        boolean running = true;
        while(running) {
            int menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println(listInv());  //lists player inventory
                    break;
                case 2:
                    //future use
                    break;
                case 3:
                    //future use
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Please try again.");
                    break;
            }
        }

    }    
}