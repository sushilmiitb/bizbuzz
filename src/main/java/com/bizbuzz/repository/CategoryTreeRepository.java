package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.CategoryTree;

@Repository
@Transactional
public interface CategoryTreeRepository extends JpaRepository<CategoryTree, Long>{
  @Query("select c "
      + "from CategoryTree c inner join c.parentCategory p "
      + "where p.id=?1 "
      + "order by c.categoryName asc")
  List<CategoryTree> findByParentCategory(Long id);
}
