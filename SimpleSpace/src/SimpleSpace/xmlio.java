/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author G89390
 */
public class xmlio {
    xmlio() throws ParserConfigurationException, SAXException, IOException{

    }
    
    public Document readXML(InputStream is) throws ParserConfigurationException, SAXException, IOException {
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
    
    public String getPrologue() throws ParserConfigurationException, SAXException, IOException{
            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("data/prologue.xml")){
            if (is != null) {
                Document prologue = readXML(is);
                NodeList nl = prologue.getDocumentElement().getElementsByTagName("mtext");
                return nl.item(0).getTextContent();
            } else return "File didn't open!";
        }        
    }
}

class NullResolver implements EntityResolver {
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
      IOException {
    return new InputSource(new StringReader(""));
  }
}