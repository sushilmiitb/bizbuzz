package com.bizbuzz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category_tree")
public class CategoryTree implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue
  private Long id;
  
  private String categoryName;
  
  /*@ManyToOne
  @JoinColumn(name="parent_category")
  private CategoryTree parentCategory;
  
  @OneToMany(mappedBy="parentCategory")
  private List<CategoryTree> childrenCategory;
  */
}
