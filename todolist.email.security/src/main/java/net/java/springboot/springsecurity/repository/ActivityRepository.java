package net.java.springboot.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.springsecurity.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

}