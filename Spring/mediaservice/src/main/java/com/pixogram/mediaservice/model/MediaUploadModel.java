package com.pixogram.mediaservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaUploadModel implements Serializable {
	private Integer userid;
	private String title;
	private String description;
	private String tags;
	private String url;
	private String type;
}
