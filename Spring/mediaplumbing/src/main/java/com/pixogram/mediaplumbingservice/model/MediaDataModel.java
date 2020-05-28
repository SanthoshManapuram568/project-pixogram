package com.pixogram.mediaplumbingservice.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.netflix.discovery.provider.Serializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MediaDataModel implements Serializable{
	
	// private MultipartFile file;
	private Integer userid;
	private String url;
	private String title;
	private String description;
	private String tags;
	private String type;
	
}
