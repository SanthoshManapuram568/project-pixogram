package com.pixogram.userservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationModel {
	private String username;
	private String fname;
	private String lname;
	private String uemail;
	private String password;
	private String profile;
}
