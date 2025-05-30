package com.data.controller;

import com.data.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

     @GetMapping("/product-list")
     public String showProductList(Model model) {
         Product product1 = new Product(1, "Product 1", 100);
         Product product2 = new Product(12, "Product 2", 1200);

         List<Product> products = Arrays.asList(product1, product2);
         model.addAttribute("products", products);

         return "product_list";
     }

     @GetMapping("/product-add")
     public String add(Model model) {
         Product product = new Product();
         model.addAttribute("product", product);

         return "product_add";
     }
}
