package com.bizbuzz.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.bizbuzz.dto.CountryCodeDTO;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;

public class HelperFunctions {
  /**
   * This function is used to get the list of node values with tagname as list of string from an xml file.
   * Test Use: HelperFunctions.retrieveResourcesAppConatants(getClass().getResourceAsStream("/application/AppStaticValues.xml"), "personrole");
   * @param inputStream: Location of the file of which config parameters are to be retrieved
   * @param tagName: tagName of the nodes of which list is sought
   * @return List of string which contains the elements with tagName
   */
 
  public static List<CountryCodeDTO> retrieveResourcesCountryCodes(InputStream inputStream, String tagName){
    List<CountryCodeDTO> countryCodelist = new ArrayList<CountryCodeDTO>();
    try {
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(inputStream);
      doc.getDocumentElement ().normalize ();  
      NodeList nodeList = doc.getElementsByTagName(tagName);
      for(int i=0; i<nodeList.getLength(); i++){
            countryCodelist.add(getCountryCodeDTO(nodeList.item(i)));
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
    return countryCodelist;
  }
  
  private static CountryCodeDTO getCountryCodeDTO(Node node) {
    CountryCodeDTO countryCodeDto = new CountryCodeDTO();
    if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
//      countryCodeDto.setAlphaNumericCode(getTagValue("alphaNumericCode", element));       If you want alpha numeric code then uncomment it
        countryCodeDto.setCountryName(getTagValue("countryName", element));
        countryCodeDto.setNumericCode(getTagValue("numericCode", element));
    }
    return countryCodeDto;
  }

  private static String getTagValue(String tag, Element element) {
    NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
    Node node = (Node) nodeList.item(0);
    return node.getNodeValue();
  }

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
  
  public static String getImageDir(InputStream inputStream){
    String baseDir = retrieveResourcesAppConatants(inputStream, "basefileuploadurl").get(0);
    //String rootDir = System.getProperty("catalina.home");
    //String rootDir = "../../../../../..";
    return baseDir;
  }
  
  public static void writeImage(String imageId, byte[] bytes, InputStream inputStream, String middleDir){
    String baseDir = retrieveResourcesAppConatants(inputStream, "basefileuploadurl").get(0);
    String rootDir = System.getProperty("catalina.home");
    File file = new File(rootDir+File.separator+"webapps/bizbuzz"+File.separator+baseDir+File.separator+middleDir+File.separator+imageId+".jpg");
    BufferedOutputStream stream = null;
    try {
      stream = new BufferedOutputStream(new FileOutputStream(file));
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      stream.write(bytes);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      stream.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public static void saveAllSizeImages(String imageId, byte[] bytes){
    SaveAllSizedImagesThread thread = new SaveAllSizedImagesThread(imageId, bytes);
    thread.start();
  }
  
//  public void fillModelWithParameterMetadata(Model m, Map<String, Map<String, Map<String, PropertyMetadata>>> propertyMap){
//    m.addAttribute("primaryImagePrimary", propertyMap.get("primary").get("image").get("primary"));
//    //adding Normal image attributes
//    List<PropertyMetadata> normalImages = (List<PropertyMetadata>)propertyMap.get("primary").get("image").values();
//    for(int i=0;i<normalImages.size();i++){
//      if(normalImages.get(i).getPropertyCode().equals("primary")){
//        normalImages.remove(i);
//        break;
//      }
//    }
//    
//  }
  
}
