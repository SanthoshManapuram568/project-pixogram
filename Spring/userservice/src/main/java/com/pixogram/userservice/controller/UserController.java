package com.pixogram.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.userservice.entity.User;
import com.pixogram.userservice.exceptions.UserErrorResponse;
import com.pixogram.userservice.exceptions.UserNotFoundException;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserData;
import com.pixogram.userservice.model.UserRegistrationModel;
import com.pixogram.userservice.repository.UserRepository;
import com.pixogram.userservice.services.IUserService;


@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	//private UserRepository userRepository;
	private IUserService userService;
	
	
	@GetMapping("/search-users/{searchText}")
	public ResponseEntity<SearchedUserModelList> getSearchedUsers(@PathVariable String searchText){
		SearchedUserModelList list =  this.userService.searchUsers(searchText);
		ResponseEntity<SearchedUserModelList> result = new ResponseEntity<SearchedUserModelList>(list, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/users")
	public ResponseEntity<UserData> getUserDetails(){
		List<User> details=this.userService.findAllUsers();
		if(details.size()==0) {
			throw new UserNotFoundException("No Users");
		}
		UserData userData=new UserData();
		userData.setData(details);
		ResponseEntity<UserData> response=new ResponseEntity<UserData>(userData, HttpStatus.OK);
		return response;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getActionById(@PathVariable Integer id){
		Optional<User> record=this.userService.findUserById(id);
		
		User users=new User();
		if(record.isPresent())
		{
			users=record.get();
		}
		else
		 {
				System.out.println("in exception");
				throw new UserNotFoundException("User with id-" + id + " not Found");
			}
		this.userService.findUserById(id);
		ResponseEntity<User> response=new ResponseEntity<User>(users,HttpStatus.OK);
		
		return response;
	
	}
	

/*	@PostMapping("/users")
	public ResponseEntity<User>boolean addUsers(@RequestBody User user)
	{
		if(!this.userService.saveuser(user))
			throw new RuntimeException("Could not post !!!");
		ResponseEntity<User> response = new ResponseEntity<User>(user,HttpStatus.OK);
		
		return true;
	}
*/
	

	@PutMapping("/users")
	public ResponseEntity<User> updateAction(@RequestBody User user)
	{
		
		if(!this.userService.updateUser(user))
			throw new RuntimeException("Could not update record!!!");
		ResponseEntity<User> response=new ResponseEntity<User>(user,HttpStatus.OK);
		
		return response;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id)
	{
		Optional<User> record=this.userService.findUserById(id);
		
		User user=new User();
		if(record.isPresent())
		{
			user=record.get();
		}
		else {

			throw new UserNotFoundException("User with id-" + id + " not Found");
		
		}
		
		this.userService.deleteUser(id);
		ResponseEntity<User> response=new ResponseEntity<User>(user,HttpStatus.OK);
		return response;
		
	}

	@ExceptionHandler  // ~catch
	public ResponseEntity<UserErrorResponse> commentNotFoundHandler(UserNotFoundException ex) {
		// create error object
		UserErrorResponse error = new UserErrorResponse(ex.getMessage(), 
															  HttpStatus.NOT_FOUND.value(), 
															  System.currentTimeMillis());
		ResponseEntity<UserErrorResponse> response =
										new ResponseEntity<UserErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<UserErrorResponse> commentOperationErrorHAndler(Exception ex) {
		// create error object
		UserErrorResponse error = new UserErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		System.out.println(error);
		ResponseEntity<UserErrorResponse> response =
										new ResponseEntity<UserErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
	
	

}
