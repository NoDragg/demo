package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PostMapping()
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return this.getProducts();
    }
    @PutMapping()
   public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        service.updateProduct(product);
        return this.getProducts();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return this.getProducts();
    }
}

