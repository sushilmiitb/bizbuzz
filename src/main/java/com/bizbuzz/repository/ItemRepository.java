package com.bizbuzz.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.Company;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;

@Transactional
@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
  @Query("select i "
      + "from Item i inner join i.owner o left join i.propertyValues "
      + "where i.id=?1 "
      + "and o.id=?2")
  Item findItemByIdAndOwnerId(Long itemId, Long ownerId);
  
  @Query("select i "
      + "from Item i inner join i.owner o left join fetch i.propertyValues "
      + "where i.id=?1 "
      + "and o.id=?2")
  Item findItemByIdAndOwnerIdWithPropertyValues(Long itemId, Long ownerId);
  
  @Query("select i "
      + "from Item i "
      + "where i.itemCategory.id=?1 "
      + "and i.owner.id=?2 "
      + "order by i.created desc")
  List<Item> findItemsByCategoryIdAndOwnerId(Long categoryId, Long ownerId);
  
  @Query("select distinct s "
      + "from Person s inner join s.toParties tp inner join s.ownedItems oi "
      + "where tp.toPartyId=?1 "
      + "and oi.itemCategory.id=?2 "
      + "order by oi.created desc")
  List<Person> findSellersByBuyerIdOrderByLatestItemUploadOfACategory(Long buyerId, Long categoryId);
  
  @Query("select distinct s "
      + "from Person s inner join s.toParties tp inner join s.ownedItems oi "
      + "where tp.toPartyId=?1 "
      + "order by oi.created desc")
  List<Person> findSellersByBuyerIdOrderByLatestItemUpload(Long buyerId);
  
  @Query("select distinct c "
      + "from Company c inner join c.toParties ctp inner join ctp.toParty s inner join s.toParties tp inner join s.ownedItems oi "
      + "where tp.toPartyId=?1 "
      + "and oi.itemCategory.id=?2 "
      + "order by oi.created desc")
  List<Company> findCompaniesSellersByBuyerIdAndCategoryIdOrderByLatestItemUpload(Long buyerId, Long categoryId);
  
  @Query("select distinct c "
      + "from Company c inner join c.toParties ctp inner join ctp.toParty s inner join s.toParties tp inner join s.ownedItems oi "
      + "where tp.toPartyId=?1 "
      + "order by oi.created desc")
  List<Company> findCompaniesSellersByBuyerIdOrderByLatestItemUpload(Long buyerId);
  
  @Query("select distinct i "
      + "from Item i inner join i.owner o inner join i.sharedToParties sp inner join sp.toParties buyerConnection "
      + "where i.itemCategory.id=?1 "
      + "and i.owner.id=?2 "
      + "and buyerConnection.toPartyId=?3 "
      + "and buyerConnection.connectionType=?4 "
      + "order by i.created desc")
  List<Item> findItemsByCategoryIdAndOwnerIdAndBuyerIdSharedThroughPrivateGroup(Long categoryId, Long ownerId, Long buyerId, ConnectionType connectionType);
  
  @Query("select distinct i "
      + "from Item i inner join i.owner o inner join o.toParties otp "
      + "where i.id=?1 "
      + "and o.id=?2 "
      + "and otp.toPartyId=?3")
  Item findItemByItemIdAndOwnerIdAndBuyerId(Long itemId, Long sellerId, Long buyerId);
  
  @Query("select pg "
      + "from PrivateGroup pg inner join pg.sharedItems si "
      + "where si.id=?1")
  List<PrivateGroup> findSharedToPrivateGroupsByItemId(Long itemId);
//  @Query("select pm "
//      + "from Item i inner join i.propertyValues pv inner join pv.propertyField pf inner join pf.propertySubGroup psg inner join psg.propertyGroup pg inner join pg.propertyMetadata pm "
//      + "where i.owner.id=?1 and "
//      + "i.id=?2")
//  PropertyMetadata findPropertyMetadataFromOwnerIdandItemIdContainingValues(Long ownerId, Long itemId);
  
//  @Query("select i "
//      + "from Person o inner join o.ownedItems i inner join itemCategory c inner join o.toParties tc inner join i.propertyValue p "
//      + "where tc.toPartyId=?2 and "
//      + "c.id=?1 and "
//      + "tc.connectionType=?3 "
//      + "order by p.updatedAt desc")
//  List<Item> findItemsByCategoryIdAndBuyerIdAndConnectionType(Long categoryId, Long buyerId, ConnectionType connectionType);
}
