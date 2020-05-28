package com.pixogram.mediaplumbingservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryDisplayResponse {
	private Integer id;
	private Integer userId;
	private String title;
	private String description;
	private String tags;
	private String type;
	private String url;
	private Integer comments;
	private Integer likes;
	private Integer disLikes;
	
	
	
}
