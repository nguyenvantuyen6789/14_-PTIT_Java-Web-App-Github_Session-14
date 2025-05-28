package com.data.controller;

import com.data.model.Product;
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
    private List<Product> products = new ArrayList<>();

    public ProductController() {
        Product product1 = new Product(1, "Hat", 1200);
        Product product2 = new Product(2, "Car", 1400);

        products.add(product1);
        products.add(product2);
    }

    @GetMapping("product-list")
    public String getAll(Model model,
                         HttpSession session) {
        model.addAttribute("products", products);

        List<Product> listProductCart = (List<Product>) session.getAttribute("cart");
        System.out.println("listProductCart1");
        System.out.println(listProductCart);

        return "product_list";
    }

    @GetMapping("product-detail/{id}")
    public String getDetail(@PathVariable int id,
                            Model model) {
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

        List<Product> listResult = products.stream()
                .filter(product -> product.getId() == id)
                .collect(Collectors.toList());
        Product product = listResult.get(0);

        // add product có id gửi lên vào list product cart
        listProductCart.add(product);
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
