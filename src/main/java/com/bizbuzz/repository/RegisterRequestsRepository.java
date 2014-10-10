package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.RegisterRequest;


@Transactional
@Repository
public interface RegisterRequestsRepository extends JpaRepository<RegisterRequest, Long>{
  List<RegisterRequest> findByToPartyPhoneNumber(String toPartyPhoneNumber);
}
