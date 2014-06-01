package com.bizbuzz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderStatus implements Serializable{
  @Id
  @GeneratedValue
  private Long id;
  private String status;
  private Person updatingPerson;
}
