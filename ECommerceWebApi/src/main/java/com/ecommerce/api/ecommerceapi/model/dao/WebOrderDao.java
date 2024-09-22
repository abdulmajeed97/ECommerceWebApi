package com.ecommerce.api.ecommerceapi.model.dao;

import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.model.WebOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderDao extends ListCrudRepository<WebOrder, Long> {


    @Query("""
     select  u from LocalUser u where u.username=:user
      """)
    List<WebOrder> findByUser(LocalUser user);
}
