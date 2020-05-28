package com.pixogram.userservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.userservice.entity.Authorities;
import com.pixogram.userservice.entity.User;
import com.pixogram.userservice.model.SearchedUserModel;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserNameModel;
import com.pixogram.userservice.model.UserRegistrationModel;
import com.pixogram.userservice.repository.AuthorityRepository;
import com.pixogram.userservice.repository.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	@Override
	public List<User> findAllUsers() {
	List<User> record =this.userRepository.findAll();
	return record;
	}

	@Override
	public Optional<User> findUserById(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> user = this.userRepository.findById(id);
		return user;
	}

	@Override

	public void saveuser(UserRegistrationModel user) {
		User data = new User();
		data.setUserName(user.getUsername());
		logger.info(user.getUsername());
		data.setFirstName(user.getFname());
		data.setLastName(user.getLname());
		data.setPassword("{noop}" + user.getPassword());
		data.setEmail(user.getUemail());
		data.setProfilePic(user.getProfile());
		data.setEnabled(true);
		this.userRepository.save(data);
		
		// add authority
		Authorities role = new Authorities(user.getUsername(), "ROLE_USER");
		this.authorityRepository.save(role);
	}
	
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		this.userRepository.save(user);
		return false;
	
	}

	@Override
	public boolean deleteUser(Integer id) {
		// TODO Auto-generated method stub
		this.userRepository.deleteById(id);
		return false;
	}
	/*
	public List<UserNameModel> findUserNames(){
		List<User> users = this.userRepository.findAll();
		List<UserNameModel> userNames = users.stream().map(user->
		{
			UserNameModel userNameModel = new UserNameModel(user.getUserName());
			return userNameModel;
		}).collect(Collectors.toList());
		
		return userNames;
	}
*/

	@Override
	public List<UserNameModel> findUserNames() {
		List<User> users = this.userRepository.findAll();
		List<UserNameModel> userNames = users.stream().map(user->
		{
			UserNameModel userNameModel = new UserNameModel(user.getUserName());
			return userNameModel;
		}).collect(Collectors.toList());
		
		return userNames;
	}

	@Override
	public SearchedUserModelList searchUsers(String searchText) {
		List<User> users = this.userRepository.findByUserNameContaining(searchText);
		List<SearchedUserModel> searchedUsers =
				users.stream()
					.map(user -> {
						SearchedUserModel searchedUser = 
								new SearchedUserModel(user.getUserId(), 
													 user.getFirstName() + " " + user.getLastName(), 
													 user.getProfilePic(), user.getUserName(),user.getEmail(),
													 false);
						return searchedUser;		
					}).collect(Collectors.toList());
		SearchedUserModelList list = new SearchedUserModelList();
		list.setUserList(searchedUsers);
		return list;
	}
}
