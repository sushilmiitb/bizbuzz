package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PropertySubGroup;

@Repository
@Transactional
public interface PropertySubGroupRepository extends JpaRepository<PropertySubGroup, Long>{
  @Modifying
  @Query("delete from PropertySubGroup p "
      + "where p.id=?1")
  void deletePropertySubGroupById(Long id);
}
