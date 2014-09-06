package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.ImageModel;

@Transactional
@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long>{
  @Modifying
  @Query("delete from ImageModel i "
      + "where i.id=?1")
  void deleteImageModelById(Long id);
  
  @Query("select im "
      + "from ImageModel im inner join im.propertyMetadata pm inner join pm.category c "
      + "where c.id=?1 "
      + "order by im.id")
  List<ImageModel> findImageModelsByCategoryIdOrderByImageModelId(Long categoryId);
}
