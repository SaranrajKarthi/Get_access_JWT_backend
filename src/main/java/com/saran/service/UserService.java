package com.saran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saran.entity.User;
import com.saran.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo repo;

	public void saveUser(User user) {
		repo.save(user);
	}
	
	public User getUserByEmail(String email) {
		return repo.findByEmail(email);
	}

	

}
