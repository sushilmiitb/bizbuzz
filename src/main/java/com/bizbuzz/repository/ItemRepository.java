package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Item;
import com.bizbuzz.model.Connection.ConnectionType;

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
  
//  @Query("select i "
//      + "from Person o inner join o.ownedItems i inner join itemCategory c inner join o.toParties tc inner join i.propertyValue p "
//      + "where tc.toPartyId=?2 and "
//      + "c.id=?1 and "
//      + "tc.connectionType=?3 "
//      + "order by p.updatedAt desc")
//  List<Item> findItemsByCategoryIdAndBuyerIdAndConnectionType(Long categoryId, Long buyerId, ConnectionType connectionType);
}
