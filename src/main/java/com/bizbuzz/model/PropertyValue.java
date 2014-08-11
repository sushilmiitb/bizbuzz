package com.bizbuzz.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("value")
public class PropertyValue extends Property{
  
  private static final long serialVersionUID = 1L;

}
