package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Party;

@Repository
@Transactional
public interface CategoryTreeRepository extends JpaRepository<CategoryTree, Long>{
  @Query("select c "
      + "from CategoryTree c inner join c.parentCategory p "
      + "where p.id=?1 "
      + "order by c.categoryName asc")
  List<CategoryTree> findByParentCategory(Long id);

  List<CategoryTree> findByOwner(Party owner);
  List<CategoryTree> findByIsCustom(Boolean isCustom);
  
  @Query("select ch "
      + "from CategoryTree c inner join c.parentCategory p inner join c.childrenCategory ch "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  List<CategoryTree> findCategoryDepthOne(Long rootId, Long categoryId);
  
  @Query("select ch2 "
      + "from CategoryTree c inner join c.parentCategory p inner join c.childrenCategory ch1 inner join ch1.childrenCategory ch2 "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  List<CategoryTree> findCategoryDepthTwo(Long rootId, Long categoryId);
  
  @Query("select ch3 "
      + "from CategoryTree c inner join c.parentCategory p inner join c.childrenCategory ch1 inner join ch1.childrenCategory ch2 inner join ch2.childrenCategory ch3 "
      + "where p.id=?1 "
      + "and c.id=?2 ")
  List<CategoryTree> findCategoryDepthThree(Long rootId, Long categoryId);
  
}
