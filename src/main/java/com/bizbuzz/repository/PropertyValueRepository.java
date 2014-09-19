package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PropertyValue;

@Transactional
@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long>{
  
}
