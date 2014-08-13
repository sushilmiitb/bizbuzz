package com.bizbuzz.service;


import java.util.List;

import com.bizbuzz.model.CategoryTree;
import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
import com.bizbuzz.model.PropertyMetadata;

public interface ItemService {
  public Item saveItem(Item item);
  public Item getItemByItemIdAndOwner(Long itemId, Person owner);
  public List<Item> getItemsByCategoryIdAndOwner(Long categoryId, Long ownerId);
  public List<Item> getItemsOfAllSellersByCategoryAndBuyer(CategoryTree category, Person buyer);
}
