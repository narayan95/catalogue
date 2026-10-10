package com.sports.catalogue.controller;

import com.sports.catalogue.entity.Catalogue;
import com.sports.catalogue.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/app")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;
    Map<Integer, Catalogue> mp= new HashMap<>();
    @PostMapping()
    void addProduct(@RequestBody Catalogue catalogue) {
        catalogueService.addProduct(catalogue);
//        mp.put(catalogue.getId(), new Catalogue(catalogue.getId(),catalogue.getName(),catalogue.getPrice()));
    }
   @GetMapping()
   List<Catalogue> getAllProduct(){
        return catalogueService.getAllProduct();

    }
    @PutMapping("/{myId}")
    Catalogue getProductById(@PathVariable int myId){
        return mp.get(myId);
    }

    @DeleteMapping("/{myId}")
    void deleteProduct(@PathVariable int myId){
    mp.remove(myId);
    }


}
