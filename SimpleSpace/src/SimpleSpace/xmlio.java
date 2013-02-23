/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.io.*;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author G89390
 */
public class xmlio {
    xmlio() { }
    
    public InputStream decompress(InputStream in) throws IOException {
        PushbackInputStream pb = new PushbackInputStream( in, 2 ); //we need a pushbackstream to look ahead
        byte [] signature = new byte[2];
        pb.read( signature ); //read the signature
        pb.unread( signature ); //push back the signature to the stream
        if( signature[ 0 ] == (byte) 0x1f && signature[ 1 ] == (byte) GZIPInputStream.GZIP_MAGIC ) //check if matches standard gzip maguc number
        return new GZIPInputStream( pb );
        else 
        return pb;        
    }
    
    public Document readXMLinJAR(String fname) throws ParserConfigurationException, SAXException, IOException {
        try (InputStream is = decompress(this.getClass().getClassLoader().getResourceAsStream(fname))){
            if (is != null) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setValidating(false);
                dbf.setIgnoringComments(false);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setNamespaceAware(true);
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                db.setEntityResolver(new NullResolver());
                return db.parse(is);
            }
            else {
                System.out.println("File read " + fname + " failed!");
                System.exit(9);
                return null;
            }
        }
    }
    
    public Document readXMLinFile(String fname) throws ParserConfigurationException, SAXException, IOException {
        try (InputStream is = new FileInputStream(fname)){
            if (is != null) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setValidating(false);
                dbf.setIgnoringComments(false);
                dbf.setIgnoringElementContentWhitespace(true);
                dbf.setNamespaceAware(true);
                DocumentBuilder db = null;
                db = dbf.newDocumentBuilder();
                db.setEntityResolver(new NullResolver());
                return db.parse(is);
            }
            else {
                System.out.println("File read " + fname + " failed!");
                System.exit(9);
                return null;
            }
        }
    }

    public void saveToXML(initGameData a, String fname) throws ParserConfigurationException, TransformerConfigurationException, TransformerException{
        Document save;
        Element e = null;
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            save = db.newDocument();
            
            Element rootElem = save.createElement("root");
            
            e = save.createElement("mtext");
            e.appendChild(save.createTextNode(a.prologueTXT));
            rootElem.appendChild(e);
            
            e = save.createElement("jumpMax");
            e.appendChild(save.createTextNode(String.valueOf(a.jumpMax)));
            rootElem.appendChild(e);
            
            e = save.createElement("jumpCtr");
            e.appendChild(save.createTextNode(String.valueOf(a.jumpCtr)));
            rootElem.appendChild(e);
            
            for(int i = 0; i < a.npcs.size(); i++){
                e = save.createElement("NPC");

                Element e_isAlive = null;
                Element e_HP = null;
                Element e_dmgModifier = null;
                Element e_lootvalue = null;
                Element e_lootrolls = null;
                Element e_initSpam = null;

                e_isAlive = save.createElement("isAlive");
                if (a.npcs.get(i).isAlive) e_isAlive.appendChild(save.createTextNode("true"));
                else e_isAlive.appendChild(save.createTextNode("false"));
                e.appendChild(e_isAlive);
                
                e_HP = save.createElement("HP");
                e_HP.appendChild(save.createTextNode(String.valueOf(a.npcs.get(i).HP)));
                e.appendChild(e_HP);
                
                e_dmgModifier = save.createElement("dmgModifier");
                e_dmgModifier.appendChild(save.createTextNode(String.valueOf(a.npcs.get(i).dmgModifier)));
                e.appendChild(e_dmgModifier);
                
                e_lootvalue = save.createElement("lootvalue");
                e_lootvalue.appendChild(save.createTextNode(String.valueOf(a.npcs.get(i).lootvalue)));
                e.appendChild(e_lootvalue);
                
                e_lootrolls = save.createElement("lootrolls");
                e_lootrolls.appendChild(save.createTextNode(String.valueOf(a.npcs.get(i).lootrolls)));
                e.appendChild(e_lootrolls);
                
                e_initSpam = save.createElement("initSpam");
                e_initSpam.appendChild(save.createTextNode(a.npcs.get(i).initSpam));
                e.appendChild(e_initSpam);
                
                rootElem.appendChild(e);
            }
            
            for(int i = 0; i < a.possibleItems.size(); i++){
                e = save.createElement("item");

                Element e_itemID = null;
                Element e_qty = null;
                Element e_title = null;
                Element e_desc = null;
                Element e_repairValue = null;
                Element e_dmgValue = null;

                e_itemID = save.createElement("itemID");
                e_itemID.appendChild(save.createTextNode(String.valueOf(a.possibleItems.get(i).itemID)));
                e.appendChild(e_itemID);
                
                e_qty = save.createElement("qty");
                e_qty.appendChild(save.createTextNode(String.valueOf(a.possibleItems.get(i).qty)));
                e.appendChild(e_qty);
                
                e_title = save.createElement("title");
                e_title.appendChild(save.createTextNode(a.possibleItems.get(i).title));
                e.appendChild(e_title);
                
                e_desc = save.createElement("desc");
                e_desc.appendChild(save.createTextNode(a.possibleItems.get(i).desc));
                e.appendChild(e_desc);
                
                e_repairValue = save.createElement("repairValue");
                e_repairValue.appendChild(save.createTextNode(String.valueOf(a.possibleItems.get(i).repairValue)));
                e.appendChild(e_repairValue);
                
                e_dmgValue = save.createElement("dmgValue");
                e_dmgValue.appendChild(save.createTextNode(String.valueOf(a.possibleItems.get(i).dmgValue)));
                e.appendChild(e_dmgValue);
                
                rootElem.appendChild(e);
            }

            for(int i = 0; i < a.plyrs.size(); i++){
                e = save.createElement("player");

                Element e_isAlive = null;
                Element e_HP = null;
                Element e_dmgModifier = null;
                Element e_lootvalue = null;
                Element e_lootrolls = null;
                Element e_initSpam = null;

                e_isAlive = save.createElement("isAlive");
                if (a.plyrs.get(i).isAlive) e_isAlive.appendChild(save.createTextNode("true"));
                else e_isAlive.appendChild(save.createTextNode("false"));
                e.appendChild(e_isAlive);
                
                e_HP = save.createElement("HP");
                e_HP.appendChild(save.createTextNode(String.valueOf(a.plyrs.get(i).HP)));
                e.appendChild(e_HP);
                
                e_dmgModifier = save.createElement("dmgModifier");
                e_dmgModifier.appendChild(save.createTextNode(String.valueOf(a.plyrs.get(i).dmgModifier)));
                e.appendChild(e_dmgModifier);
                
                e_lootvalue = save.createElement("lootvalue");
                e_lootvalue.appendChild(save.createTextNode(String.valueOf(a.plyrs.get(i).lootvalue)));
                e.appendChild(e_lootvalue);
                
                e_lootrolls = save.createElement("lootrolls");
                e_lootrolls.appendChild(save.createTextNode(String.valueOf(a.plyrs.get(i).lootrolls)));
                e.appendChild(e_lootrolls);
                
                e_initSpam = save.createElement("initSpam");
                e_initSpam.appendChild(save.createTextNode(a.plyrs.get(i).initSpam));
                e.appendChild(e_initSpam);
                
                rootElem.appendChild(e);
            }            
             
            save.appendChild(rootElem);
            
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // send DOM to file
            tr.transform(new DOMSource(save), 
                                 new StreamResult(new FileOutputStream(fname)));

        } catch (TransformerException te) {
            System.out.println(te.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }            
    }
    
}

class NullResolver implements EntityResolver {
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
      IOException {
    return new InputSource(new StringReader(""));
  }
}