package com.ecommerce.api.ecommerceapi.api.controller.product;

import com.ecommerce.api.ecommerceapi.model.Product;
import com.ecommerce.api.ecommerceapi.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;


    @GetMapping
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }


}
