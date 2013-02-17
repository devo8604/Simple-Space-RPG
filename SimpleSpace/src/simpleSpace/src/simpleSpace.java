package simpleSpace.src;


/**
 * @version 0.01
 * @author Devon Smith
 */
import java.util.*;

public class simpleSpace {
    
    /*
     * Class variable declarations
     */
    public double HP; // default = 3000
    public int gameJumpCTR; // default = 1
    public int gameJumpMAX; //default = 15

    simpleSpace() {
        HP = 3000;
        gameJumpCTR = 1;
        gameJumpMAX = 15;
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
    
    public void mnMenu() throws InterruptedException {
        Scanner in = new Scanner(System.in);
        event ev = new event();
        item item = new item();

        for (; gameJumpCTR < gameJumpMAX; gameJumpCTR++) {
            while (HP > 0) {
                System.out.println("|----------------|");
                System.out.println("|  Select one:   |");
                System.out.println("|  1. Jump.      |");
                System.out.println("|  2. Inventory  |");
                System.out.println("|  3. Quit.      |");
                System.out.println("|----------------|");

                int menuItem = in.nextInt();

                switch (menuItem) {
                    case 1:
                        double battleChoice = ev.ranNum();

                        if (battleChoice > 80) {
                            eneFrigate fr = new eneFrigate();

                            fr.battleStart();
                            Thread.sleep(250);
                            fr.battle();
                            mnMenu();
                        } else if (battleChoice > 40) {
                            eneBattleship bs = new eneBattleship();

                            bs.battleStart();
                            Thread.sleep(250);
                            bs.battle();
                            mnMenu();
                        } else {
                            eneCruiser cr = new eneCruiser();

                            cr.battleStart();
                            Thread.sleep(250);
                            cr.battle();
                            mnMenu();
                        }
                    case 2:
                        item.inventory();

                        break;

                    case 3:
                        System.exit(0);

                        break;
                }
            }

            System.out.println("You have failed. GAME OVER");
            System.exit(0);
        }

        System.out.println("Congratulations Captain, you have successfully brought\n"
                + "your crew home safely, for the most part, and delivered your precious\n"
                + "cargo to the alliance fleet headquarters. The war will soon be over because\n"
                + "of you.");
        System.exit(0);
    }
    
    public static void main(String[] args) throws InterruptedException, Exception {
        simpleSpace game = new simpleSpace();
        game.processor(args);
        System.out.println("In a Galaxy far far far away in the distant future,\n"
                + "you are the Captain of an alliance battleship, trapped deep\n"
                + "within enemy territory with Intelligence information that could\n"
                + "finally put an end to this war. In order to make it back to Alliance\n"
                + "space, you must successfully make 15 FTL jumps. That is 15 times\n"
                + "that the enemy has the oppurtunity to stop you, and turn the tide\n"
                + "of the war in their favor...");
        game.mnMenu();
    }


}


//~ Formatted by Jindent --- http://www.jindent.com
