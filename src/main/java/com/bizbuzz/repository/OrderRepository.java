package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
}