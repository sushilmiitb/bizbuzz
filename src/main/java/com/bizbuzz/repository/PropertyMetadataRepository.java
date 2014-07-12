package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PropertyMetadata;

@Transactional
@Repository
public interface PropertyMetadataRepository extends JpaRepository<PropertyMetadata, Long>{
  @Query("select p "
      + "from PropertyMetadata p inner join p.category c "
      + "where c.id=?1")
  List<PropertyMetadata> findPropertyMetadataByCategoryId(Long categoryId);
}
