package com.ecommerce.api.ecommerceapi.service;

import com.ecommerce.api.ecommerceapi.model.Product;
import com.ecommerce.api.ecommerceapi.model.dao.ProductDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductDao productDao;
    public List<Product> getAllProducts(){
        return productDao.findAll();
    }


}
