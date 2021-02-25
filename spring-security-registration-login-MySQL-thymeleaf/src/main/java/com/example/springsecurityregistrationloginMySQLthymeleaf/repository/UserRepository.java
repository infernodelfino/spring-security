package com.example.springsecurityregistrationloginMySQLthymeleaf.repository;

import com.example.springsecurityregistrationloginMySQLthymeleaf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
