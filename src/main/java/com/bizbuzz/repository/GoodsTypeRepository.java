package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.GoodsType;

@Repository
@Transactional
public interface GoodsTypeRepository extends JpaRepository<GoodsType, Long> {
  
  @Query("select specificName from  GoodsType")
  public List<String> findAllSpecificName();
}