package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.PropertyField;

@Repository
@Transactional
public interface PropertyFieldRepository extends JpaRepository<PropertyField, Long> {
  @Modifying
  @Query("delete from PropertyField p "
      + "where p.id=?1")
  void deletePropertyFieldById(Long id);
  
  @Query("select pf "
      + "from PropertyField pf inner join pf.propertySubGroup psg inner join psg.propertyGroup pg inner join pg.propertyMetadata pm "
      + "where pm.category.id = ?1 "
      + "order by pf.id asc")
  List<PropertyField> findPropertyFieldsByCategoryIdOrderByPropertyFieldId(Long categoryId);
}