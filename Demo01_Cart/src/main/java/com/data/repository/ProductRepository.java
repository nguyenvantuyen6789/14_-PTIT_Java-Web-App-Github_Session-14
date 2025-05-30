package com.data.repository;

import com.data.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
}
