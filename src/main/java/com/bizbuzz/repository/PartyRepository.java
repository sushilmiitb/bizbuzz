package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Party;

@Repository
@Transactional
public interface PartyRepository extends JpaRepository<Party, Long>{
  
}