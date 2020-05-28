package com.pixogram.userservice.controller;


import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.userservice.entity.User;
import com.pixogram.userservice.model.ResponseData;
import com.pixogram.userservice.model.UserNameModel;
import com.pixogram.userservice.model.UserRegistrationModel;
import com.pixogram.userservice.repository.UserRepository;
import com.pixogram.userservice.services.IUserService;
import com.pixogram.userservice.services.StorageService;
import com.pixogram.userservice.services.UserServiceImpl;
@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {
	@Autowired
	private IUserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StorageService storageService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@GetMapping("/login")
	public ResponseEntity<ResponseData> login(HttpServletRequest request) {
		// if called then credentials are valid
		
		String authorization = request.getHeader("Authorization");
		String[] values = null;
		
		if (authorization != null && authorization.startsWith("Basic")) {
		    // Authorization: Basic base64credentials
		    String base64Credentials = authorization.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    // credentials = username:password
		    values = credentials.split(":", 2);
		}
		
		
		logger.info("Logged In...");
		logger.info(values[0]);
		logger.info(values[1]);
        
        User user = this.userRepository.findByuserName(values[0]).get(0);
        logger.info("User : " + user);
        // add any additional information like firstname, lastname, profilepic etc
		
        ResponseData data = new ResponseData("Welcome!!!", System.currentTimeMillis(), user.getUserId(),user.getProfilePic(),user.getFirstName(),user.getLastName(),user.getEmail());

		ResponseEntity<ResponseData> response = 
					new ResponseEntity<ResponseData>(data, HttpStatus.OK);
		
		return response;
		
	}
	/*
	
	@PostMapping("/register")
	public ResponseEntity<ResponseData> register(@RequestBody UserRegistrationModel userInput,Principal principal) {
	logger.info("Registration...");	
	
	this.userService.saveuser(userInput);
	User user = this.userRepository.findByuserName(principal.getName()).get(0);
	ResponseData data = new ResponseData("Welcome!!!", System.currentTimeMillis(),user.getUserId());

	ResponseEntity<ResponseData> response = 

				new ResponseEntity<ResponseData>(data, HttpStatus.OK);
	return response;

	}
*/
	
	@PostMapping("/register") // POST HTTP VERB
	// public ResponseEntity<Product> save(@RequestBody Product product)
	public ResponseEntity<UserRegistrationModel> save(@RequestParam("file") MultipartFile file,
			@RequestParam("userName") String userName, 
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("profilePic") String profilePic
			) {
		
		// explicitly need to create product object
		
		UserRegistrationModel user = new UserRegistrationModel();
		user.setUsername(userName);	
		user.setFname(firstName);
		user.setLname(lastName);
		user.setPassword(password);
		user.setUemail(email);
		user.setProfile(profilePic);
		this.userService.saveuser(user);
		this.storageService.store(file);
		
		return null;
	}
	
	@GetMapping("/usernames")
		public ResponseEntity<List<UserNameModel>> findUserNames(){
		List<UserNameModel> userNames = this.userServiceImpl.findUserNames();
		ResponseEntity<List<UserNameModel>> response = new ResponseEntity<List<UserNameModel>>(userNames,HttpStatus.OK);
		
		return response;
	}
}
