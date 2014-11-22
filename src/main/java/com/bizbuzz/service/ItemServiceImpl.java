package com.bizbuzz.service;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Connection.ConnectionType;
import com.bizbuzz.model.ImageModel;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Party;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;
import com.bizbuzz.model.PropertyValue;
import com.bizbuzz.repository.ImageModelRepository;
import com.bizbuzz.repository.ItemRepository;
import com.bizbuzz.repository.PropertyValueRepository;

@Service
public class ItemServiceImpl implements ItemService{
  @Autowired
  ImageModelRepository imageModelRepository;
  @Autowired
  PropertyValueRepository propertyValueRepository;
  @Autowired
  ItemRepository itemRepository;
  
  public Item saveItem(Item item){
    return itemRepository.saveAndFlush(item);
  }
  
  public Item getItemByItemIdAndOwner(Long itemId, Person owner){
    return itemRepository.findItemByIdAndOwnerId(itemId, owner.getId());
  }
  
  @Transactional
  public Item getItemByItemIdAndOwnerWithImagesAndPropertyValues(Long itemId, Person owner){
    Item item = itemRepository.findItemByIdAndOwnerIdWithPropertyValues(itemId, owner.getId());
    Hibernate.initialize(item.getImageModels());
    return item;
  }
  
  public List<Item> getItemsByCategoryIdAndOwner(Long categoryId, Long ownerId){
    return itemRepository.findItemsByCategoryIdAndOwnerId(categoryId, ownerId);
  }
  
  @Transactional
  public List<Item> getItemsByCategoryIdAndOwnerWithImages(Long categoryId, Long ownerId){
    List<Item> itemList = itemRepository.findItemsByCategoryIdAndOwnerId(categoryId, ownerId);
    for (Item item : itemList) {
      Hibernate.initialize(item.getImageModels());
    }
    return itemList;
  }
   
  public List<Item> getItemsOfAllSellersByCategoryAndBuyer(CategoryTree category, Person buyer){
    //return itemRepository.findItemsByCategoryIdAndBuyerIdAndConnectionType(category.getId(), buyer.getId(), ConnectionType.SELLER_BUYER);
    return null;
  }
  
  public List<Person> getSellersByBuyerIdOrderByLatestItemUpload(Person buyer, CategoryTree categoryTree){
    return itemRepository.findSellersByBuyerIdOrderByLatestItemUpload(buyer.getId(), categoryTree.getId());
  }
  
  public List<Company> getCompaniesOfSellersByBuyerIdOrderByLatestItemUpload(Person buyer, CategoryTree categoryTree){
    return itemRepository.findCompaniesSellersByBuyerIdOrderByLatestItemUpload(buyer.getId(), categoryTree.getId());
  }
  
  public List<Item> getItemsByCategoryIdAndOwnerAndBuyer(Long categoryId, Long sellerId, Long buyerId){
    return itemRepository.findItemsByCategoryIdAndOwnerIdAndBuyerIdSharedThroughPrivateGroup(categoryId, sellerId, buyerId, ConnectionType.GROUP_MEMBERS);
  }
  
  @Transactional
  public List<Item> getItemsByCategoryIdAndOwnerAndBuyerWithImageModels(Long categoryId, Long sellerId, Long buyerId){
    List<Item> items = itemRepository.findItemsByCategoryIdAndOwnerIdAndBuyerIdSharedThroughPrivateGroup(categoryId, sellerId, buyerId, ConnectionType.GROUP_MEMBERS);
    for (Item item : items) {
      Hibernate.initialize(item.getImageModels());
    }
    return items;
  }

  @Override
  public Item getItemByItemId(Long itemId) {
    return itemRepository.findOne(itemId);
  }
  
  public Item getItemByItemIdAndOwnerAndBuyer(Long itemId, Long sellerId, Long buyerId){
    return itemRepository.findItemByItemIdAndOwnerIdAndBuyerId(itemId, sellerId, buyerId);
  }
  
  @Transactional
  public Item getItemByItemIdAndOwnerAndBuyerWithImageModelsAndPropertyValues(Long itemId, Long sellerId, Long buyerId){
    Item item = itemRepository.findItemByItemIdAndOwnerIdAndBuyerId(itemId, sellerId, buyerId);
    Hibernate.initialize(item.getImageModels());
    Hibernate.initialize(item.getPropertyValues());
    return item;
  }
  
  public Item populateItemWithSharedPrivateGroups(Item item, Map<Long, PrivateGroup> groupMap, Long[] sharedGroupIds){
    List<Party> sharedList = new ArrayList<Party>();
    for(int i=0; sharedGroupIds!=null && i<sharedGroupIds.length; i++){
      if(groupMap.get(sharedGroupIds[i])!=null){
        sharedList.add(groupMap.get(sharedGroupIds[i]));
      }
    }
    item.setSharedToParties(sharedList);
    return item;
  }
  
  public List<PrivateGroup> getSharedPrivateGroups(Item item){
    return itemRepository.findSharedToPrivateGroupsByItemId(item.getId());
  }
}