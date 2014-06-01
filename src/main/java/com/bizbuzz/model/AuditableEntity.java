package com.bizbuzz.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *  
 *  @author mukesh
 */
@Deprecated
@MappedSuperclass
public abstract class AuditableEntity {
  @Id
  @GeneratedValue
  private Long id;
  public Date createdAt;
  public Date updatedAt;

  public Long getId() {
    return id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  @PrePersist
  void createdAt() {
    this.createdAt = this.updatedAt = new Date();
  }

  @PreUpdate
  void updatedAt() {
    this.updatedAt = new Date();
  }

}
