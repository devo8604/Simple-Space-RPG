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
    
    public void setNPCs() {

    }
}
