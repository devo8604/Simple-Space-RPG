package SimpleSpace;

/**
 * @version 0.01
 * @author Devon Smith
 */
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class simpleSpace {

    /*
     * Class variable declarations
     */
    public ArrayList<entity> encounters;
    public GameData data;

    simpleSpace() throws ParserConfigurationException, SAXException, IOException {
        data = new GameData();
        encounters = new ArrayList();
        for (int i = 0; i < data.jumpMax; i++) {
            encounters.add(new entity(data.npcs.get(data.gen.nextInt(data.npcs.size()))));
        }
        System.out.println(data.getPrologue());
    }
    /*
     * show help function. just uses system.out.println but is used multiple times.
     */

    public void showHelp() {
        System.out.println("\n\n***Usage Guidelines***\n\n"
                + "\t-c:\tIndicate the campaign file.\n"
                + "\t-i:\tIndicate the items file.\n"
                + "\t-n:\tIndicate the npc file.\n"
                + "\t-p:\tIndicate the player file. This will be used for savegames, too.\n"
                + "\t-h:\tShow this help message. This can also be triggered with --help.\n");
        System.exit(0);
    }
    
    public void printMenu() {
        System.out.println("|--------------------|");
        System.out.println("|  Select one:       |");
        System.out.println("|  1. Jump           |");
        System.out.println("|  2. Inventory      |");
        System.out.println("|  3. Load Campaign  |");
        System.out.println("|  4. Quit           |");
        System.out.println("|--------------------|");        
    }

    /*
     *  default function for handling switches. takes 2 strings, and parses them as switches.
     *  it sets variables appropriate to the modifiers specified by the switches.
     *  returns true if successful.
     */
    public boolean switchHandler(String arg, String param) throws Exception { //arg array instruction handler
        if (arg.equalsIgnoreCase("-c")) { //if arg is -c, then ....
            return true;
        } else if (arg.equalsIgnoreCase("-f")) { //if arg is -f, ....
            return true;
        } else if (arg.equalsIgnoreCase("-l")) { //if arg is -l, ....
            return true;
        } else if (arg.equalsIgnoreCase("-p")) { //if arg is -p, ....
            return true;
        } else if (arg.equalsIgnoreCase("-h")) { //if arg is -h, echo help      
            showHelp();
            return true;
        } else if (arg.equals("--")) {
            if (param.equalsIgnoreCase("listen")) {
                return true;
            } else if (param.equalsIgnoreCase("help")) {
                showHelp();
                return true;
            } else {
                System.out.println("Invalid switch detected!");
                return false;
            }
        } else {
            return false;
        }
    }

    /*
     * argument preprocessor. takes args array and parses it into chunks for the switch handler.
     * does some extra parsing / error checking to save cycles.
     */
    public void processor(String[] args) throws Exception {
        //switchHandler Processor
        for (int i = 0; i < args.length; i++) {
            if (args[i].length() > 2 && !args[i].substring(0, 2).equals("--")) {
                switchHandler(args[i].substring(0, 2), args[i].substring(2));
            } else if (args[i].length() == 2) {
                switchHandler(args[i].substring(0, 2), args[i + 1]);
                i++;
            } else {
                System.out.println("Invalid switch detected!");
            }
        }
    }

    public void startGameEvent() throws InterruptedException, ParserConfigurationException, SAXException, IOException {
        while (data.plyrs.get(0).isAlive && data.jumpCtr < data.jumpMax) {
            if (data.plyrs.get(0).isAlive) {
                printMenu();
                int menuItem = data.input.nextInt();

                switch (menuItem) {
                    case 1:
                        data.jumpCtr++;
                        System.out.println(encounters.get(data.jumpCtr).initSpam);
                        if (data.battle(data.plyrs.get(0), encounters.get(data.jumpCtr))) 
                            startGameFailEvent();
                        break;
                    case 2:
                        data.inventoryMenu();
                        break;

                    case 3:
                        try {
                            data.open();
                        } catch ( ParserConfigurationException | SAXException | IOException ex) {
                            data.logger.append(simpleSpace.class.toString());
                        }
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Self Desctruct Activated.");
                        for (int i = 5; i > 0; --i) {
                            System.out.println("Self destruct in " + i + " seconds.");
                            Thread.sleep(1000);
                        }
                        System.exit(0);
                }
            } else {
                startGameFailEvent();
            }
        }
        if (data.jumpCtr == data.jumpMax && data.plyrs.get(0).isAlive) {
            startGameSuccessEvent();
        }
    }

    public void startGameSuccessEvent() {
        System.out.println("Congratulations Captain, you have successfully brought\n"
                + "your crew home safely, for the most part, and delivered your precious\n"
                + "cargo to the alliance fleet headquarters. The war will soon be over because\n"
                + "of you.");
        System.exit(0);
    }

    public void startGameFailEvent() {
        System.out.println("You have failed. GAME OVER");
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException, Exception {
        simpleSpace game = new simpleSpace();
        game.processor(args);
        game.startGameEvent();
    }
}
