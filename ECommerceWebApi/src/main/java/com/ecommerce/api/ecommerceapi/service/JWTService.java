package com.ecommerce.api.ecommerceapi.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.api.ecommerceapi.model.LocalUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

// jwt is json web token, it's encrypted string
// is used usually to passed as a credential verifier
@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private Long expiryInSeconds;

    private Algorithm algorithm;
    private static final String USERNAME="USERNAME";


    @PostConstruct
    public void setAlgorithmKey(){
        algorithm =Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(LocalUser user){
        return JWT.create()
                .withClaim(USERNAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }


    public String getUsername(String token){
       return JWT.decode(token).getClaim(USERNAME).asString();
    }



}
