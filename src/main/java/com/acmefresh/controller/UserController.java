package com.acmefresh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acmefresh.model.User;
import com.acmefresh.model.UserSession;
import com.acmefresh.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("/users/signup")
	public ResponseEntity<User> saveUserHandler(@Valid @RequestBody User user){
		
		User savedUser = uService.saveUser(user);
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/users/login")
	public ResponseEntity<UserSession> getUserHandler(@RequestBody User user){
		
		UserSession userLoggedin = uService.saveLogin(user);
		return new ResponseEntity<UserSession>(userLoggedin,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/users/{key}")
	public ResponseEntity<User> updateUserHandler(@RequestBody User user,@PathVariable String key){
		
		User updatedUser = uService.updateUser(user, key);
		return new ResponseEntity<User>(updatedUser,HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/users/logout/{key}")
	public ResponseEntity<String> deleteUserHandler(@PathVariable String key){
		uService.deleteSession(key);
		return new ResponseEntity<String>("Logged out successfully",HttpStatus.ACCEPTED);
	}

}
