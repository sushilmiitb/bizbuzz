package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long itemId;
  
  private String itemNumber;
  private String styleNumber;
  
  @ManyToOne
  @JoinColumn(name="category_id")
  private CategoryTree category;
}
