package com.sports.catalogue.service;

import com.sports.catalogue.entity.Catalogue;
import com.sports.catalogue.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;

    public void addProduct(Catalogue catalogue){
        catalogueRepository.save(catalogue);
    }
    public List<Catalogue> getAllProduct(){
            return catalogueRepository.findAll();
    }

}
