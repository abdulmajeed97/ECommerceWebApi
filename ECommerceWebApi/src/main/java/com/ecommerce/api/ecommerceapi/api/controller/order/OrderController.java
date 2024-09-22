package com.ecommerce.api.ecommerceapi.api.controller.order;

import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.model.WebOrder;
import com.ecommerce.api.ecommerceapi.service.OrderService;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    // All orders to the user that signed in
    // that is why we used @AuthenticationPrincipal  that holds the LocalUser auth object
    @GetMapping
    public List<WebOrder> getAllOrder(@AuthenticationPrincipal LocalUser user){
        return orderService.getAllOrders(user);
    }

}
