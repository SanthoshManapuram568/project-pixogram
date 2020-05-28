package com.pixogram.userservice.services;

import java.util.List;
import java.util.Optional;

import com.pixogram.userservice.entity.User;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserNameModel;
import com.pixogram.userservice.model.UserRegistrationModel;

public interface IUserService {
	List<User> findAllUsers();
	Optional<User> findUserById(Integer id);
	public void saveuser(UserRegistrationModel userInput);
	List<UserNameModel> findUserNames();
	boolean updateUser(User user);
	boolean deleteUser(Integer id);
	public SearchedUserModelList searchUsers(String searchString);
}
