package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PrivateGroup;

@Repository
@Transactional
public interface PrivateGroupRepository extends JpaRepository<PrivateGroup, Long>{
}
