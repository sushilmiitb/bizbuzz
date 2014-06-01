package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.ContactUs;
import com.bizbuzz.model.UserRegistration;

@Repository
@Transactional
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}