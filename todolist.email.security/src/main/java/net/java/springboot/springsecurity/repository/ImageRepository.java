package net.java.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.springsecurity.model.User;

@Repository
public interface ImageRepository extends JpaRepository<User, Long> {

}