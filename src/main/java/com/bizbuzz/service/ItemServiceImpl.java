package com.bizbuzz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbuzz.model.Item;
import com.bizbuzz.model.Person;
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
  
  public List<Item> getItemsByCategoryIdAndOwner(Long categoryId, Long ownerId){
    return itemRepository.findItemsByCategoryIdAndOwnerId(categoryId, ownerId);
  }
  
}