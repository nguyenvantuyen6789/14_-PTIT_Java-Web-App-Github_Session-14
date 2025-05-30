package com.data.controller;

import com.data.model.Product;
import com.data.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product-list")
    public String getAll(Model model,
                         HttpSession session) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);

        return "product_list";
    }

    @GetMapping("product-detail/{id}")
    public String getDetail(@PathVariable int id,
                            Model model) {
        List<Product> products = productService.getAll();

        List<Product> listResult = products.stream()
                .filter(product -> product.getId() == id)
                .collect(Collectors.toList());
        Product product = listResult.get(0);

        model.addAttribute("product", product);

        return "product_detail";
    }

    @GetMapping("add-to-cart/{id}")
    public String addToCart(@PathVariable int id,
                            Model model,
                            HttpSession session) {
        // lần đầu list product cart k có dữ liệu
        List<Product> listProductCart = (List<Product>) session.getAttribute("cart");
        if (listProductCart == null) {
            listProductCart = new ArrayList<>();
        }

        List<Product> products = productService.getAll();

        long count = products.stream()
                .filter(product -> product.getId() == id)
                .count();
        if (count == 0) {
            Product product = products.stream().filter(o -> o.getId() == id)
                    .findFirst().get();
            // add product có id gửi lên vào list product cart
            listProductCart.add(product);
        }

        // add list product cart vào lại session (key=cart)
        session.setAttribute("cart", listProductCart);

        return "product_list";
    }

    @GetMapping("show-cart")
    public String showCart(HttpSession session,
                           Model model) {
        // lấy list product cart trong session
        List<Product> listProductCart = (List<Product>) session.getAttribute("cart");
        model.addAttribute("products", listProductCart);

//        int totalPrice = listProductCart.stream().reduce(0, (a, b) -> a.getPrice() + b.getPrice());
        double totalPrice = listProductCart.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        model.addAttribute("totalPrice", totalPrice);

        return "cart_list";
    }

}
