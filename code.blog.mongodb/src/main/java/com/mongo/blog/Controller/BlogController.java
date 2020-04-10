package com.mongo.blog.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.blog.Model.User;
import com.mongo.blog.Repository.BlogRepository;

@RestController
@RequestMapping("/users")
public class BlogController {
	
	private BlogRepository blogrepository;
	
	public BlogController(BlogRepository blogrepository) {
		this.blogrepository = blogrepository;
	}
	
	@GetMapping("/all")
	public List<User> getAll(){
	List <User> users = this.blogrepository.findAll();
	
	return users;
	}
	
	@PutMapping
    public void insert(@RequestBody User user){
        this.blogrepository.insert(user);
    }
	
	 @PostMapping
	    public void update(@RequestBody User user){
	        this.blogrepository.save(user);
	    }
	 
	 @GetMapping("/{id}")
	    public User getById(@PathVariable("id") int id){
	        User user = this.blogrepository.findById(id);

	        return user;
	    }
	 
	 @GetMapping("/price/{firstName}")
	    public List<User> getByPricePerNight(@PathVariable("maxPrice") String firstName){
	        List<User> users = this.blogrepository.findByFirstName(firstName);

	        return users;
	    }
	 
	 @GetMapping("/title/{title}")
	    public List<User> getByTitle(@PathVariable("title") String title){
	        List<User> users = this.blogrepository.findByTitle(title);

	        return users;
	    }

}
