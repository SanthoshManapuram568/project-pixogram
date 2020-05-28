package com.pixogram.commentservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentErrorResponse {
	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
}
