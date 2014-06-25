package com.bizbuzz.service;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.Connection.ConnectionType;

public interface ConnectionService {
  public void createConnection(Person person, Company company, ConnectionType connectionType);

}
