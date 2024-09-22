package com.ecommerce.api.ecommerceapi.service;

import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.model.WebOrder;
import com.ecommerce.api.ecommerceapi.model.dao.WebOrderDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private WebOrderDao webOrderDao;

    public List<WebOrder> getAllOrders(LocalUser user){
        return webOrderDao.findByUser(user);
    }


}
