package com.ecommerce.api.ecommerceapi.service;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {

    /*When a password is stored in a database, it's essential to hash it instead of storing it in plain text to protect it from unauthorized access. However, using simple hashing algorithms like MD5 or SHA-1 alone is not sufficient because they are vulnerable to brute-force attacks and dictionary attacks.

To mitigate these risks, salts are added to passwords before hashing. A salt is a randomly generated value that is unique for each password. By adding a salt, even if two users have the same password, their hashed passwords will be different due to the unique salt.

Salt rounds refer to the number of times a hashing algorithm is applied to a password along with the salt. It's essentially the number of iterations of the hash function. Increasing the number of salt rounds increases the computational effort required to hash passwords, making brute-force attacks significantly more time-consuming and resource-intensive.

For example, if you use bcrypt for password hashing, you can specify the number of salt rounds. The higher the number of salt rounds, the longer it takes to compute the hash, thus making it harder for attackers to crack passwords.

In summary, salt rounds are a security measure used to strengthen password hashing by adding a unique salt and applying multiple iterations of the hash function to make brute-force attacks more difficult.*/

    @Value("${encryption.salt.round}")
    private Integer saltRounds;

    private String salt;

    @PostConstruct
    public void createSalt(){
        salt= BCrypt.gensalt(saltRounds);
    }

    public String encryptPassword(String password){
        return BCrypt.hashpw(password,salt);
    }


    public Boolean verifyPassword(String password , String hash){
        return BCrypt.checkpw(password,hash);
    }
}
