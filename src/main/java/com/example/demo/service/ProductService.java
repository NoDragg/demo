package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Component
public class ProductService {
    @Autowired
    private ProductRepo repo;


    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.getReferenceById(id);
    }
    public void addProduct(Product product) {
        repo.save(product);
    }
    public void updateProduct(Product product){
        repo.save(product);
    }
    public void deleteProduct(int id){
        repo.deleteById(id);
    }
}