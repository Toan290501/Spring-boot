package org.example.spring_boot.controller;


import org.example.spring_boot.models.Products;
import org.example.spring_boot.models.ResponseObject;
import org.example.spring_boot.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping(path = "/api/home")

public class HomeController {
    private final ProductRepository repository;

    public HomeController(ProductRepository repository) {
        this.repository = repository;
    }

    //get list products
    @GetMapping("/getAllProduct")
    List<Products> getAllProduct() {
        return repository.findAll();
    }

    //get detail product
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable int id) {
        Optional<Products> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "query product succesfuly", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "cannot find product with id" + id, "")
            );

        }
    }

    // insert product
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Products newProduct) {
        List<Products> foundProduct = repository.findByName(newProduct.getName().trim());
        if (!foundProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("false", "cannot insert product with name (duplicate)" + newProduct.getName(), "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "insert product succesfuly", repository.save(newProduct))
        );
    }

    // update
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable int id, @RequestBody Products newproduct) {
        Products updatedProduct = repository.findById(id)
                .map(products -> {
                    products.setName(newproduct.getName());
                    products.setPrice(newproduct.getPrice());
                    return repository.save(products);
                }).orElseGet(() -> {
                    newproduct.setId(id);
                    return repository.save(newproduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "update product succesfuly", updatedProduct)
        );
    }

    // delete
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable int id) {
        boolean exists = repository.existsById(id);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "cannot delete product with id" + id, "")
            );
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "delete product succesfuly", "")
        );
    }

}
