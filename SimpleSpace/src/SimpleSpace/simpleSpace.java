package SimpleSpace;


/**
 * @version 0.01
 * @author Devon Smith
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import sun.misc.IOUtils;

public class simpleSpace {
    
    /*
     * Class variable declarations
     */
    public ArrayList<entity> encounters;
    public initGameData data = new initGameData();

    simpleSpace() throws ParserConfigurationException, SAXException, IOException {
        data.jumpCtr = 0;
        data.jumpMax = 15;
        encounters = new ArrayList();
        for (int i=0; i<data.jumpMax; i++) {
            encounters.add(new entity(data.npcs.get(data.gen.nextInt(data.npcs.size()))));
        }
        System.out.println(data.getPrologue());
    }
    /*
     * show help function. just uses system.out.println but is used multiple times.
     */
    public void showHelp() {
        System.out.println("\n\n***Usage Guidelines***\n\n" +
                    "\t-c:\tIndicate the campaign file.\n" +
                    "\t-i:\tIndicate the items file.\n" +
                    "\t-n:\tIndicate the npc file.\n" +
                    "\t-p:\tIndicate the player file. This will be used for savegames, too.\n" +
                    "\t-h:\tShow this help message. This can also be triggered with --help.\n" );
        System.exit(0);
    }
    
    /*
     *  default function for handling switches. takes 2 strings, and parses them as switches.
     *  it sets variables appropriate to the modifiers specified by the switches.
     *  returns true if successful.
     */
    public boolean switchHandler(String arg, String param) throws Exception { //arg array instruction handler
        if(arg.equalsIgnoreCase("-c")) { //if arg is -c, then ....
            return true;
        } else if(arg.equalsIgnoreCase("-f")){ //if arg is -f, ....
            return true;
        } else if(arg.equalsIgnoreCase("-l")){ //if arg is -l, ....
            return true;  
        } else if(arg.equalsIgnoreCase("-p")){ //if arg is -p, ....
            return true;
        } else if(arg.equalsIgnoreCase("-h")){ //if arg is -h, echo help      
            showHelp();
            return true;
        } else if (arg.equals("--")) {
                if (param.equalsIgnoreCase("listen")){
                    return true;
                } else if (param.equalsIgnoreCase("help")) {
                    showHelp();
                    return true;
                } else {
                    System.out.println("Invalid switch detected!");
                    return false;
                }
        }
        else return false;
    }
    
    /*
     * argument preprocessor. takes args array and parses it into chunks for the switch handler.
     * does some extra parsing / error checking to save cycles.
     */
    public void processor(String[] args) throws Exception {
    //switchHandler Processor
        for(int i = 0; i < args.length; i++) {
            if(args[i].length() > 2 && !args[i].substring(0,2).equals("--")){
                switchHandler(args[i].substring(0,2), args[i].substring(2));
            } else if(args[i].length() == 2) {
                switchHandler(args[i].substring(0,2), args[i+1]);
                i++;
            } else System.out.println("Invalid switch detected!");
        }
    }
    
    public void startGameEvent(Scanner in) throws InterruptedException {
        while (data.plyrs.get(0).isAlive) {
            if (data.plyrs.get(0).isAlive) {
                    System.out.println("|--------------------|");
                    System.out.println("|  Select one:       |");
                    System.out.println("|  1. Jump           |");
                    System.out.println("|  2. Inventory      |");
                    System.out.println("|  3. Load Campaign  |");
                    System.out.println("|  4. Quit           |");
                    System.out.println("|--------------------|");

                    int menuItem = in.nextInt();

                    switch (menuItem) {
                        case 1:
                            data.jumpCtr++;
                            System.out.println(encounters.get(data.jumpCtr).initSpam);
                            while (encounters.get(data.jumpCtr).isAlive) {
                                Thread.sleep(250);
                                data.plyrs.get(0).battle(encounters.get(data.jumpCtr));
                            }
                            if (!encounters.get(data.jumpCtr).isAlive && data.plyrs.get(0).isAlive) {
                                System.out.println("Success! You vanquished " + encounters.get(data.jumpCtr).name + "! \n And now for the loot!");
                                data.plyrs.get(0).loot(encounters.get(data.jumpCtr));
                            }
                            else startGameFailEvent();
                        case 2:
                                    
                            break;

                        case 3:
                            try {
                                data.open();
                            } catch (ParserConfigurationException ex) {
                                Logger.getLogger(simpleSpace.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SAXException ex) {
                                Logger.getLogger(simpleSpace.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(simpleSpace.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case 4: 
                            System.exit(0);
                            break;
                        default: 
                            System.out.println("Self Desctruct Activated.");
                            for(int i = 5; i > 0; --i){ 
                            System.out.println("Self destruct in " + i + " seconds.");
                            Thread.sleep(1000);}
                            System.exit(0);
                    }
            }
            else {
                startGameFailEvent();
            }
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
    
    public void mnMenu() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        for (; data.jumpCtr < data.jumpMax; data.jumpCtr++) {
                startGameEvent(in);
        }
        if (data.plyrs.get(0).isAlive) { startGameSuccessEvent();}
        else { startGameFailEvent(); }
    }
    
    public static void main(String[] args) throws InterruptedException, Exception {
        simpleSpace game = new simpleSpace();
        game.processor(args);
        game.mnMenu();
    }


}
