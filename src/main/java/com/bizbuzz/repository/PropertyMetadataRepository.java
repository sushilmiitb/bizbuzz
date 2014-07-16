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
//  @Query("select p "
//      + "from PropertyMetadata p inner join p.category c "
//      + "where c.id=?1")
//  List<PropertyMetadata> findPropertyMetadataByCategoryId(Long categoryId);
//  
//  @Query("select prop "
//      + "from c.parentCategory p inner join CategoryTree c inner join c.properties prop "
//      + "where p.id=?1 "
//      + "and c.id=?2 "
//      + "order by prop.groupingName3, prop.groupingName2, prop.groupingName1 ")
//  List<PropertyMetadata> findCategoryDepthOne(Long rootId, Long categoryId);
//  
//  @Query("select prop "
//      + "from c.parentCategory p inner join CategoryTree c inner join c.childrenCategory ch inner join ch.properties prop "
//      + "where p.id=?1 "
//      + "and c.id=?2 "
//      + "prop.groupingName3, prop.groupingName2, prop.groupingName1")
//  List<PropertyMetadata> findCategoryDepthTwo(Long rootId, Long categoryId);
//  
//  @Query("select prop "
//      + "from c.parentCategory p inner join CategoryTree c inner join c.childrenCategory ch inner join ch.children ch1 inner join ch1.properties prop "
//      + "where p.id=?1 "
//      + "and c.id=?2 "
//      + "prop.groupingName3, prop.groupingName2, prop.groupingName1")
//  List<PropertyMetadata> findCategoryDepthThree(Long rootId, Long categoryId);
  
  @Query("select p "
      + "from PropertyMetadata p inner join p.category c "
      + "where c.id=?1")
  PropertyMetadata findPropertyMetadataByCategoryId(Long categoryId);
}
