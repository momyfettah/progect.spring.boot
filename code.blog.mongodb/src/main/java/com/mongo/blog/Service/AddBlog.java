package com.mongo.blog.Service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import com.mongo.blog.Model.Post;
import com.mongo.blog.Model.User;
import com.mongo.blog.Repository.BlogRepository;

@Component
public class AddBlog  implements CommandLineRunner {
	
	private BlogRepository blogrepository;
	
	public AddBlog (BlogRepository blogrepository) {
		this.blogrepository = blogrepository;
	}
	@Override
	public void run (String...strings) throws Exception {
		User momy = new User(
				"Momy",
				"Bestione",
				"1998-02-03",
				Arrays.asList(new Post ("Fine del mondo",
						"Astronomia",
						"gli alieni conquisteranno i terrestri a fine aprile 2020.",
						"2020-04-04 23:23")	
						)
				);
		User naomi = new User(
				"Naomi",
				"kakarot",
				"1996-08-05",
				Arrays.asList(new Post ("Nuova Era",
						"Fantascienza",
						"gli alieni ci aiuteranno a risanare a fine aprile 2020 la terra e tutte le sue creature.",
						"2020-04-04 23:23")	
						)
				);
		
		// drop all users
        this.blogrepository.deleteAll();

        //add our users to the database
        List<User> users = Arrays.asList(momy,naomi);
        this.blogrepository.save(users);
	}

}
