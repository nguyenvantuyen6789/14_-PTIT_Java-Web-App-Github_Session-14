package com.data.repository;

import com.data.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private List<Product> products = new ArrayList<>();

    public ProductRepositoryImpl() {
        Product product1 = new Product(1, "Hat", 1200);
        Product product2 = new Product(2, "Car", 1400);

        products.add(product1);
        products.add(product2);
    }

    public List<Product> getAll() {
        return products;
    }
}
