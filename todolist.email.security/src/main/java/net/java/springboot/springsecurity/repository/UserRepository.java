package net.java.springboot.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.springsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository < User, Long > {
    public User findByEmail(String email);
   
}

