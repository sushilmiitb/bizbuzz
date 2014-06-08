package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Connection;

@Repository
@Transactional
public interface ConnectionRepository extends JpaRepository<Connection, Long>{
  
}
