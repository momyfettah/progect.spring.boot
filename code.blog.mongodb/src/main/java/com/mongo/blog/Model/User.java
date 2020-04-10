package com.mongo.blog.Model;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Users")
public class User {
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String birthday;
	
	private List<Post> post;
	
	public User() {
		this.post = new ArrayList<>();  
    }
	
	
	public User (String firstName, String lastName, String birthday,List<Post> post) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.post = post;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String setLastName() {
		return lastName;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String setBirthday() {
		return birthday;
	}
	
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public List<Post> getPost() {
		return post;
	}
	
	

}
