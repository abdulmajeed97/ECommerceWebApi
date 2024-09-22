package com.ecommerce.api.ecommerceapi.model.dao;

import com.ecommerce.api.ecommerceapi.model.LocalUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.lang.Long;
import java.util.Optional;


public interface LocalUserDao extends CrudRepository<LocalUser,Long > {
    @Query("""
       SELECT l FROM LocalUser l where l.username = :username
""")
    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    @Query("""
       SELECT l FROM LocalUser l where l.email = :email
""")
    Optional<LocalUser> findByEmailIgnoreCase(String email);

}
