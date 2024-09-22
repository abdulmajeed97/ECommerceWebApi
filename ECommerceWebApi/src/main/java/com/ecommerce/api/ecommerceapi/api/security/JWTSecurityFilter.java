package com.ecommerce.api.ecommerceapi.api.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.ecommerce.api.ecommerceapi.model.LocalUser;
import com.ecommerce.api.ecommerceapi.model.dao.LocalUserDao;
import com.ecommerce.api.ecommerceapi.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@Component
public class JWTSecurityFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final LocalUserDao localUserDao;

    @Autowired
    public JWTSecurityFilter(JWTService jwtService, LocalUserDao localUserDao) {
        this.jwtService = jwtService;
        this.localUserDao = localUserDao;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        // the token comes starts with Bearer

        if (null != tokenHeader && tokenHeader.startsWith("Bearer ")) {
            // you will district this Bearer
            String token = tokenHeader.substring(7);
            try {
                // you try to extract the username from the token
                String username = jwtService.getUsername(token);
                // then here you will find the user by username
                Optional<LocalUser> opUser=localUserDao.findByUsernameIgnoreCase(username);
                if(opUser.isPresent()){
                    LocalUser user= opUser.get();
                  // this object that can hold your user object
                UsernamePasswordAuthenticationToken authenticationToken= authenticationToken(user,null,new ArrayList());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            } catch (JWTDecodeException ex) {
                    ex.getCause();
            }
        }
        // please do the next filter
        filterChain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken authenticationToken(LocalUser user
            , String password, ArrayList list){
        return new UsernamePasswordAuthenticationToken(user,password,null);

    }
}
