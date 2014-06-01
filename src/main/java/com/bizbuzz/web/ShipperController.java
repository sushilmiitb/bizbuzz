package com.bizbuzz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bizbuzz.model.Order;
import com.bizbuzz.service.ShipperService;


@Controller
public class ShipperController {
  
  @Autowired
  private ShipperService shipperService;
  
  @RequestMapping(value={"/shipper"}, method = RequestMethod.GET)
  public String shipper(){
    return "shipper";
  }
  
  @RequestMapping(value={"/shipper/bookTruck"}, method = RequestMethod.GET)
  public String bookTruck(Model  m){
    shipperService.getBookTruckFormModel(m);
    return "bookTruck";
  }
  
  @RequestMapping(value={"/shipper/bookTruck"}, method = RequestMethod.POST)
  public String bookTruck(@ModelAttribute Order order){
    shipperService.saveBookTruckForm(order);
    return "home";
  }
}