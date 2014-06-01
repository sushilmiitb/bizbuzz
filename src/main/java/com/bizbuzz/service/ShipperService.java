package com.bizbuzz.service;

import org.springframework.ui.Model;

import com.bizbuzz.model.Order;

public interface ShipperService {
  public void getBookTruckFromHome(Model m);
  public void getBookTruckFormModel(Model m);
  public void saveBookTruckForm(Order order);
}
