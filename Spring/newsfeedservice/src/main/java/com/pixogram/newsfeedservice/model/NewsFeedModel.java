package com.pixogram.newsfeedservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsFeedModel {

	private Integer userId;
	
	private String feed;
	
	private String createdOn;
}
