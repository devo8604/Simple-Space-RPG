/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
            
            save.appendChild(rootElem);
            
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

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