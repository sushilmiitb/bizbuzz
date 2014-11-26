package com.bizbuzz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.Party;
import com.bizbuzz.model.RegisterDevice;
import com.bizbuzz.repository.RegisterDeviceRepository;

@Service
public class RegisterDeviceServiceImpl implements RegisterDeviceService {

  @Autowired
  RegisterDeviceRepository registerDeviceRepository;

  public void saveRegisterDevice(RegisterDevice registerDevice) {
     registerDeviceRepository.save(registerDevice);
  }

  public RegisterDevice getRegisterDeviceByPartyId(Long partyId) {
    return registerDeviceRepository.findByPartyId(partyId);
  }
  
  
}
