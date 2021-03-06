package com.bizbuzz.service;


import java.util.List;
import java.util.Map;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Company;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PrivateGroup;
import com.bizbuzz.model.PropertyMetadata;

public interface ItemService {
  public Item saveItem(Item item);
  public Item getItemByItemIdAndOwner(Long itemId, Person owner);
  public Item getItemByItemIdAndOwnerWithImagesAndPropertyValues(Long itemId, Person owner);
  public List<Item> getItemsByCategoryIdAndOwner(Long categoryId, Long ownerId);
  public List<Item> getItemsByCategoryIdAndOwnerWithImages(Long categoryId, Long ownerId);
  public List<Item> getItemsOfAllSellersByCategoryAndBuyer(CategoryTree category, Person buyer);
  public List<Person> getSellersByBuyerIdOrderByLatestItemUpload(Person buyer);
  public List<Person> getSellersByBuyerIdAndCategoryIdOrderByLatestItemUpload(Person buyer, CategoryTree categoryTree);
  public List<Company> getCompaniesOfSellersByBuyerIdAndCategoryIdOrderByLatestItemUpload(Person buyer, CategoryTree categoryTree);
  public List<Company> getCompaniesOfSellersByBuyerIdOrderByLatestItemUpload(Person buyer);
  public List<Item> getItemsByCategoryIdAndOwnerAndBuyer(Long categoryId, Long sellerId, Long buyerId);
  public List<Item> getItemsByCategoryIdAndOwnerAndBuyerWithImageModels(Long categoryId, Long sellerId, Long buyerId);
  public Item getItemByItemIdAndOwnerAndBuyer(Long itemId, Long sellerId, Long buyerId);
  public Item getItemByItemIdAndOwnerAndBuyerWithImageModelsAndPropertyValues(Long itemId, Long sellerId, Long buyerId);
  public Item getItemByItemId(Long itemId);
  public Item getItemByItemIdWithImageModels(Long itemId);
  public Item populateItemWithSharedPrivateGroups(Item item, Map<Long, PrivateGroup> groupMap, Long[] sharedGroupIds);
  public List<PrivateGroup> getSharedPrivateGroups(Item item);
}
