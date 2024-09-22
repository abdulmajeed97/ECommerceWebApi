package com.ecommerce.api.ecommerceapi.api.controller.auth;

import com.ecommerce.api.ecommerceapi.api.model.LoginBody;
import com.ecommerce.api.ecommerceapi.api.model.LoginResponse;
import com.ecommerce.api.ecommerceapi.api.model.RegistrationBody;
import com.ecommerce.api.ecommerceapi.exception.UserAlreadyExistException;
import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginResponse response;

    @RequestMapping("/register")
    public ResponseEntity<RegistrationBody> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping("/login")
    public ResponseEntity<LoginResponse> loginUser( @Valid @RequestBody LoginBody loginBody) throws UsernameNotFoundException {
         String jwt = userService.loginUser(loginBody);
         if(null==jwt){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }

             response.setJwt(jwt);
             return ResponseEntity.ok(response);

    }

    @RequestMapping("/me")
    public LocalUser getLoggedInUser(@AuthenticationPrincipal LocalUser user){
        return user;

    }
}
