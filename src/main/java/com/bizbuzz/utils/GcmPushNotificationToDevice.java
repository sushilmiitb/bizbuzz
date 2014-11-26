package com.bizbuzz.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizbuzz.dto.PushNotificationContentDTO;

public class GcmPushNotificationToDevice implements Runnable{

  public static final Logger LOG = LoggerFactory.getLogger(GcmPushNotificationToDevice.class);
  Thread t;
  PushNotificationContentDTO content;
  
  public GcmPushNotificationToDevice(PushNotificationContentDTO content){
    this.content = content;
  }
  
  @Override
  public void run() {
    // TODO Auto-generated method stub
    pushNotification();
  }
  
  public void start(){
    if(t==null){
      t = new Thread(this);
      t.start();
    }
  }
  
  private void pushNotification(){

    String apiKey = "AIzaSyA1PJWtlSNMjX5e42f1_ePFlDnvZvQhTG4";
    try{
    // 1. URL
    URL url = new URL("https://android.googleapis.com/gcm/send");

    // 2. Open connection
   //  System.setProperty("http.proxyHost", "10.1.23.99");
   // System.setProperty("http.proxyPort","8080" );
   // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.1.23.99", 8080));
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    // 3. Specify POST method
    conn.setRequestMethod("POST");

    // 4. Set the headers
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setRequestProperty("Authorization", "key="+apiKey);

    conn.setDoOutput(true);

        // 5. Add JSON data into POST request body

        //`5.1 Use Jackson object mapper to convert Contnet object into JSON
          ObjectMapper mapper = new ObjectMapper();
          mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
        // 5.2 Get connection output stream
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

        // 5.3 Copy Content "JSON" into
        mapper.writeValue(wr, content);

        // 5.4 Send the request
        wr.flush();

        // 5.5 close
        wr.close();

        // 6. Get the response
        int responseCode = conn.getResponseCode();
        LOG.info("\nSending 'POST' request to URL : " + url);
        LOG.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 7. Print result
        LOG.info(response.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}
