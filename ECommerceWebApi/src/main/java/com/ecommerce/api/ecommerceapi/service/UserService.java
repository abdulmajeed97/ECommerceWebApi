package com.ecommerce.api.ecommerceapi.service;

import com.ecommerce.api.ecommerceapi.api.model.LoginBody;
import com.ecommerce.api.ecommerceapi.api.model.RegistrationBody;
import com.ecommerce.api.ecommerceapi.exception.UserAlreadyExistException;
import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.model.dao.LocalUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private LocalUser localUser;
    private final LocalUserDao localUserDao;

    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    @Autowired
    public UserService(LocalUserDao localUserDao, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDao = localUserDao;
        this.encryptionService=encryptionService;
        this.jwtService = jwtService;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {
        // object from local user

        // here i used 2 ifs because I want the message to be different
        // to make the user knows which one is invalid exactly , username, email
        if (localUserDao.findByEmailIgnoreCase(registrationBody.getEmail())
                .isPresent()) throw new UserAlreadyExistException("Sorry, This Email is already exist");

        else if (localUserDao.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent())
            throw new UserAlreadyExistException("Sorry, This Username is already exist");

        localUser.setFirstName(registrationBody.getFirstName());
        localUser.setLastName(registrationBody.getLastName());
        localUser.setUsername(registrationBody.getUsername());
        localUser.setEmail(registrationBody.getEmail());
        localUser.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return localUserDao.save(localUser);


    }

    public String loginUser(LoginBody loginBody) throws UsernameNotFoundException {
        Optional<LocalUser> opUser=localUserDao.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
         LocalUser user=opUser.get();
         if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
           return jwtService.generateJWT(user);
         }
        }else throw new UsernameNotFoundException("Not valid username");

        return null;

    }

}
