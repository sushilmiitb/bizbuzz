package com.bizbuzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bizbuzz.model.SecurityGroup;


@Repository
@Transactional(readOnly=true)
public interface SecurityGroupRepository extends JpaRepository<SecurityGroup, Long> {

}