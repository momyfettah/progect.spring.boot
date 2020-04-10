package com.mongo.blog.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongo.blog.Model.User;

@Repository
public interface BlogRepository extends MongoRepository {
	User findById(int id);
	
	List<User> findByFirstName(String firstName);
	
	@Query(value = "{post.title:?0}")
    List<User> findByTitle(String title);

}
