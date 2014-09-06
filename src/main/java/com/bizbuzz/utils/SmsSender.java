package com.bizbuzz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsSender {

  
  public static String sendSms(String mobileNo,String msg){

    String authkey = "71018ALFVaZrXG7c53eef2a4";
    String mobiles = mobileNo;
                                                      //Sender ID,While using route4 sender id should be 6 characters long.
    String senderId = "bizbuz";
   // String message = msg;
    String route="4";

    //Prepare Url
    URLConnection myURLConnection=null;
    URL myURL=null;
    BufferedReader reader=null;

    //encoding message 
    String encoded_message=URLEncoder.encode(msg);

    //Send SMS API
    String mainUrl="https://control.msg91.com/sendhttp.php?";

    //Prepare parameter string 
    StringBuilder sbPostData= new StringBuilder(mainUrl);
    sbPostData.append("authkey="+authkey); 
    sbPostData.append("&mobiles="+mobiles);
    sbPostData.append("&message="+encoded_message);
    sbPostData.append("&sender="+senderId);
    sbPostData.append("&route="+route);
    

    //final string
    mainUrl = sbPostData.toString();
    
    String response=null;
    try
    {
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.32.2", 8080));
        //prepare connection
        myURL = new URL(mainUrl);
        myURLConnection = myURL.openConnection(proxy);
        myURLConnection.connect();
        reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        //reading response 
        
        while ((response = reader.readLine()) != null) 
 //print response 
          System.out.println(response);
 //finally close connection
        reader.close();
    } 
    catch (IOException e) 
    { 
      e.printStackTrace();
    }
    return response; 
  }
  
}
