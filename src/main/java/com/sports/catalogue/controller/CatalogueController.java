package com.sports.catalogue.controller;

import com.sports.catalogue.entity.Catalogue;
import com.sports.catalogue.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/app")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;
    @PostMapping()
    ResponseEntity<Catalogue> addProduct(@RequestBody Catalogue catalogue) {
        try{
            catalogueService.addProduct(catalogue);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return  new ResponseEntity<>(catalogue, HttpStatus.OK);



//        mp.put(catalogue.getId(), new Catalogue(catalogue.getId(),catalogue.getName(),catalogue.getPrice()));
    }
   @GetMapping()
   ResponseEntity <List<Catalogue>> getAllProduct(){
        List<Catalogue> entries= catalogueService.getAllProduct();
        if(entries.size()>0){
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{myId}")
    ResponseEntity<Catalogue> getProductById(@PathVariable String myId){
        Optional<Catalogue> entry = catalogueService.getProductById(myId);
        if(entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{myId}")
    ResponseEntity<Catalogue> deleteProduct(@PathVariable String myId){

        if(catalogueService.getProductById(myId).isPresent()){
            catalogueService.removeProduct(myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{myId}")
    ResponseEntity<Catalogue> updateProduct(@RequestBody Catalogue catalogue, @PathVariable String myId) {

//        name price
      Optional<Catalogue> entry= catalogueService.getProductById(myId);

      if(entry.isPresent()){
          if( !(catalogue.getName().equals(entry.get().getName()))) {
              entry.get().setName(catalogue.getName());
          }
          if( catalogue.getPrice()!= entry.get().getPrice()) {
              entry.get().setPrice(catalogue.getPrice());

          }
          catalogueService.addProduct(entry.get());
            return new ResponseEntity<>(entry.get(),HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }





}
