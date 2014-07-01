package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Connection;
import com.bizbuzz.model.Connection.ConnectionType;

@Repository
@Transactional
public interface ConnectionRepository extends JpaRepository<Connection, Long>{
  List<Connection> findByFromPartyIdAndConnectionType(Long fromPartyId, ConnectionType connectionType);
}
