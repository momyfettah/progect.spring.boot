package com.mongo.blog.Model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class Post {
	
	private String title;
	
	private String topic;
	private String comment;
	
	
	private String postDate;
	
	protected Post() {
		
	}

	public Post (String title, String topic, String comment, String postDate) {
		
		this.title = title;
		this.topic = topic;
		this.comment = comment;
		this.postDate = postDate;
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	public String getPostDate() {
		return postDate;
	}

}
