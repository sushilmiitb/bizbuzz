package com.bizbuzz.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class HelperFunctions {
  /**
   * This function is used to get the list of node values with tagname as list of string from an xml file.
   * Test Use: HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppStaticValues.xml"), "personrole");
   * @param inputStream: Location of the file of which config parameters are to be retrieved
   * @param tagName: tagName of the nodes of which list is sought
   * @return List of string which contains the elements with tagName
   */
  public static List<String> retrieveResourcesAppConatants(InputStream inputStream, String tagName){
    List<String> list = new ArrayList<String>();
    try {
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(inputStream);
      doc.getDocumentElement ().normalize ();  
      NodeList nodeList = doc.getElementsByTagName(tagName);
      for(int i=0; i<nodeList.getLength(); i++){
        Node node = (Node) nodeList.item(i);
        String nodeValue =  node.getTextContent();
        list.add(nodeValue);
      }
    }catch (SAXParseException err) {
      System.out.println ("** Parsing error" + ", line " 
           + err.getLineNumber () + ", uri " + err.getSystemId ());
      System.out.println(" " + err.getMessage ());

    }catch (SAXException e) {
      Exception x = e.getException ();
      ((x == null) ? e : x).printStackTrace ();
    }catch (Throwable t) {
      t.printStackTrace ();
    }
    return list;
  }
}
