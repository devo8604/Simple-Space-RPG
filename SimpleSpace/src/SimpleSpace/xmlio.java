/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleSpace;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.PushbackInputStream;

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

    
}

class NullResolver implements EntityResolver {
  public InputSource resolveEntity(String publicId, String systemId) throws SAXException,
      IOException {
    return new InputSource(new StringReader(""));
  }
}