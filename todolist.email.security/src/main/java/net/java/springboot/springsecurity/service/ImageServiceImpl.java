
package net.java.springboot.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.java.springboot.springsecurity.model.User;
import net.java.springboot.springsecurity.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepos;
	
	@Override
	public User recoverFile(Long id) {
		return imageRepos.findById(id).orElseThrow(() -> null); // se il risultato della ricerca è null, restituisce null, altrimenti restituisce il risultato
	}
}