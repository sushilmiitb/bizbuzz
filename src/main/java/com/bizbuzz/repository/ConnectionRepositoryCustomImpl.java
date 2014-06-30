package com.bizbuzz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bizbuzz.model.PrivateGroup;

public class ConnectionRepositoryCustomImpl implements ConnectionRepositoryCustom{
  
  @PersistenceContext
  private EntityManager em;
}
