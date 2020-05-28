package com.pixogram.mediaservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaErrorResponse {
	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
}
