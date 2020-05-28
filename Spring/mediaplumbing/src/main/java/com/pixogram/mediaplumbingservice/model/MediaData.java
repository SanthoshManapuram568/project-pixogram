package com.pixogram.mediaplumbingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MediaData {
		
	private Integer id;
	private Integer userId;
	private String title;
	private String description;
	private String tags;
	private String type;
	private String url;
	
}
