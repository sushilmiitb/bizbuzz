package com.bizbuzz.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PushNotificationContentDTO implements Serializable{

  private static final long serialVersionUID = 1L;
  
  private List<String> registration_ids;
  private Map<String,String> data;

  public void addDeviceRegId(String regId){
      if(registration_ids == null)
          registration_ids = new LinkedList<String>();
      registration_ids.add(regId);
  }

  public void createData(String title, String message){
      if(data == null)
          data = new HashMap<String,String>();

      data.put("title", title);
      data.put("message", message);
  }
  
}
