package com.sports.catalogue.repository;

import com.sports.catalogue.entity.Catalogue;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CatalogueRepository extends  MongoRepository<Catalogue,String> {



}
