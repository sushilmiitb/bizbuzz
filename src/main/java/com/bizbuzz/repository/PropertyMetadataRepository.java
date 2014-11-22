package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.PropertyMetadata;

@Transactional
@Repository
public interface PropertyMetadataRepository extends JpaRepository<PropertyMetadata, Long>{
  
  @Query("select prop "
      + "from CategoryTree c inner join c.parentCategory p inner join c.propertyMetadata prop "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  PropertyMetadata findCategoryDepthOne(Long rootId, Long categoryId);
  
  @Query("select prop "
      + "from CategoryTree c inner join c.parentCategory p inner join c.childrenCategory ch inner join ch.propertyMetadata prop "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  PropertyMetadata findCategoryDepthTwo(Long rootId, Long categoryId);
  
  @Query("select prop "
      + "from CategoryTree c inner join c.parentCategory p inner join c.childrenCategory ch inner join ch.childrenCategory ch1 inner join ch1.propertyMetadata prop "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  PropertyMetadata findCategoryDepthThree(Long rootId, Long categoryId);
  
//  @Query("select p "
//      + "from PropertyMetadata p inner join p.category c left join p.imageModels im left join p.propertyGroups pg left join pg.propertySubGroups psg left join psg.propertyFields pf "
//      + "where c.id=?1")
//  PropertyMetadata findPropertyMetadataByCategoryId(Long categoryId);
  
  @Query("select p "
      + "from PropertyMetadata p "
      + "where p.category.id=?1")
  PropertyMetadata findPropertyMetadataByCategoryId(Long categoryId);
}
