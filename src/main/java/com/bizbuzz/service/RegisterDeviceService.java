package com.bizbuzz.service;

import com.bizbuzz.model.Chat;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.RegisterDevice;

public interface RegisterDeviceService {
  
  public void saveRegisterDevice(RegisterDevice registerDevice);
  public RegisterDevice getRegisterDeviceByPartyId(Long partyId);
  
}
