package com.cargopacers.test.repository;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bizbuzz.model.GoodsType;
import com.bizbuzz.repository.GoodsTypeRepository;

import static org.junit.Assert.*;

/**
 *  
 *  @author mukesh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/*.xml")
@Transactional
public class GoodsTypeRepositoryTest {

  @Autowired
  private GoodsTypeRepository goodsTypeRepository;

  @Test
  public void testSave(){
    GoodsType goodsType = new GoodsType();
    goodsType.setSpecificName("deadBody");
    goodsType.setIsBrittle(true);
    
    goodsTypeRepository.save(goodsType);
    
    GoodsType goodsTypeSaved = goodsTypeRepository.getOne(goodsType.getId());
    
    assertNotNull(goodsTypeSaved);
    assertEquals(goodsType.getSpecificName(), goodsTypeSaved.getSpecificName());
  }
  
  @Test
  public void testFindAllSpecificName(){
    GoodsType goodsType = new GoodsType();
    goodsType.setSpecificName("deadBody");
    goodsType.setIsBrittle(true);
    
    goodsTypeRepository.save(goodsType);
    List<String> specificNames = goodsTypeRepository.findAllSpecificName();
    assertNotNull(specificNames);
    assertEquals(1, specificNames.size());
    assertEquals("deadBody", specificNames.get(0));
  }
}
