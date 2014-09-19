package com.bizbuzz.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("private_group")

public class PrivateGroup extends Party{
  private static final long serialVersionUID = 1L;
  
  private String privateGroupName;

  public String getPrivateGroupName() {
    return privateGroupName;
  }

  public void setPrivateGroupName(String privateGroupName) {
    this.privateGroupName = privateGroupName;
  }
  
}
