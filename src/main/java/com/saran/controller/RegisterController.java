package com.saran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.saran.dto.ResponseDTO;
import com.saran.entity.User;
import com.saran.service.UserService;

@RestController
public class RegisterController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseDTO createUser(@RequestBody User user) {
		ResponseDTO response = new ResponseDTO();
		
		User existingUser = userService.getUserByEmail(user.getEmail());
		if(existingUser != null) {
			response.setStatusCode("Error - 400");
			response.setMessage("Email already exists");
			return response;
		}
		userService.saveUser(user);
		response.setStatusCode("Success - 200");
		response.setMessage("User Created Successfully");
		return response;
	}
}
