package com.bizbuzz.service;

import org.springframework.ui.Model;

import com.bizbuzz.model.ContactUs;

public interface ContactUsService {

  public void getContactUsFormModelDetails(Model m);

  public void save(ContactUs contactUs);

}
