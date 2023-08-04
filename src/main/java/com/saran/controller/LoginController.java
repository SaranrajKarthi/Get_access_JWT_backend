package com.saran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saran.dto.ResponseDTO;
import com.saran.entity.Login;
import com.saran.entity.User;
import com.saran.repo.LoginRepo;
import com.saran.service.JwtService;
import com.saran.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api")
public class LoginController {

	@Autowired
	LoginRepo loginRepo;

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;

	@PostMapping("/login")
	public ResponseDTO loginUser(@RequestBody User user) {
		ResponseDTO response = new ResponseDTO();

		User existingUser = userService.getUserByEmail(user.getEmail());

		if (existingUser == null) {
			response.setStatusCode("Error - 404");
			response.setMessage("User with E-mail does not exist");
			return response;
		}

		if (!existingUser.getPassword().equals(user.getPassword())) {
			response.setStatusCode("Error - 401");
			response.setMessage("Invalid Password");
			return response;
		}
		String token = jwtService.generateToken(user.getEmail());

		Login login = new Login();
		login.setEmail(user.getEmail());
		login.setPassword(user.getPassword());
		login.setToken(token);

		loginRepo.save(login);

		response.setStatusCode("Success - 200");
		response.setMessage("User Login Successfully");
		response.setToken(login.getToken());
		return response;
	}
}
