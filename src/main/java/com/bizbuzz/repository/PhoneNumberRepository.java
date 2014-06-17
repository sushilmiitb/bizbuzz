package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PhoneNumber;

@Repository
@Transactional
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>{

}
