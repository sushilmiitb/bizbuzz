package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PropertyGroup;

@Transactional
@Repository
public interface PropertyGroupRepository extends JpaRepository<PropertyGroup, Long>{
  @Modifying
  @Query("delete from PropertyGroup p "
      + "where p.id=?1")
  void deletePropertyGroupById(Long id);
}
