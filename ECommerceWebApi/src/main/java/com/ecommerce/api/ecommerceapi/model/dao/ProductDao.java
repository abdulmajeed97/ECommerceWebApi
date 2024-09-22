package com.ecommerce.api.ecommerceapi.model.dao;

import com.ecommerce.api.ecommerceapi.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductDao extends ListCrudRepository<Product, Long> {

}
