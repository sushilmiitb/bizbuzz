package com.bizbuzz.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bizbuzz.model.ImageModel;

@Transactional
@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel, Long>{
}
