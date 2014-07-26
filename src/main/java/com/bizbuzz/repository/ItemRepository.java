package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Item;

@Transactional
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
  @Query("select i "
      + "from Item i inner join i.owner o "
      + "where i.id=?1 "
      + "and o.id=?2")
  Item findItemByIdAndOwnerId(Long itemId, Long ownerId);
  
  @Query("select i "
      + "from Item i "
      + "where i.itemCategory.id=?1 "
      + "and i.owner.id=?2 ")
  List<Item> findItemsByCategoryIdAndOwnerId(Long categoryId, Long ownerId);
}
