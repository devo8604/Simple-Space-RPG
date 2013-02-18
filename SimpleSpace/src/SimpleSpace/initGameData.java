/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author G89390
 */
public class initGameData {
    public xmlio fileData = new xmlio();
    public String prologueTXT;
    public ArrayList<entity> npcs = new ArrayList();
    
    initGameData() {
        try {
            setPrologue();
            setNPCs();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(initGameData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(initGameData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(initGameData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPrologue() throws ParserConfigurationException, SAXException, IOException{
        Document prologue = fileData.readXMLinJAR("data/prologue.xml");
        NodeList nl = prologue.getDocumentElement().getElementsByTagName("mtext");
        prologueTXT = nl.item(0).getTextContent();
    }
    
    public String getPrologue() {
        return prologueTXT;
    }
    
    public void setNPCs() throws ParserConfigurationException, SAXException, IOException {
        Document NPCDATA = fileData.readXMLinJAR("data/NPCs.xml");
        NodeList nl = NPCDATA.getDocumentElement().getElementsByTagName("NPC");
        for(int ctr = 0;ctr < nl.getLength(); ctr++) {
            npcs.add(new entity());
            for(int i = 0; i < nl.item(ctr).getChildNodes().getLength(); i++) {
                if(nl.item(ctr).getChildNodes().item(i).getLocalName() != null) {
                    if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("isAlive")) {
                        npcs.get(ctr).isAlive = true;
                        System.out.println("Found isAlive value! Setting NPC's isAlive value to: " +
                                npcs.get(ctr).isAlive);
                    }
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("HP")) {
                        npcs.get(ctr).HP = Double.parseDouble(nl.item(ctr).getChildNodes().item(i).getTextContent());
                        System.out.println("Found HP value! Setting NPC's HP value to: " +
                                npcs.get(ctr).HP);
                    }
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("dmgModifier")) {
                        npcs.get(ctr).dmgModifier = Double.parseDouble(nl.item(ctr).getChildNodes().item(i).getTextContent());
                        System.out.println("Found dmgModifier value! Setting NPC's dmgModifier value to: " +
                                npcs.get(ctr).dmgModifier);
                    }
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("lootvalue")) {
                        npcs.get(ctr).lootvalue = Integer.parseInt(nl.item(ctr).getChildNodes().item(i).getTextContent());
                        System.out.println("Found loot value! Setting NPC's loot value to: " +
                                npcs.get(ctr).lootvalue); 
                    }   
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("lootrolls")) {
                        npcs.get(ctr).lootrolls = Integer.parseInt(nl.item(ctr).getChildNodes().item(i).getTextContent());
                        System.out.println("Found loot roll value! Setting NPC's loot roll value to: " +
                                npcs.get(ctr).lootrolls);
                    }
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("name")) {
                        npcs.get(ctr).name = nl.item(ctr).getChildNodes().item(i).getTextContent();
                        System.out.println("Found name value! Setting NPC's name value to: " +
                                npcs.get(ctr).name); 
                    }
                    else if(nl.item(ctr).getChildNodes().item(i).getLocalName().equals("initSpam")) {
                        npcs.get(ctr).initSpam = nl.item(ctr).getChildNodes().item(i).getTextContent();
                        System.out.println("Found initSpam value! Setting NPC's initSpam value to: " +
                                npcs.get(ctr).initSpam);  
                    }                    
                }
            }
        }

    }
}
