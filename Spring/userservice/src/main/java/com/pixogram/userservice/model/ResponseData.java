package com.pixogram.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	private String message;
	private Long timeStamp;
	private Integer userId;
	private String profilePic; 
	private String firstName;
	private String LastName;
	private String email;
}
