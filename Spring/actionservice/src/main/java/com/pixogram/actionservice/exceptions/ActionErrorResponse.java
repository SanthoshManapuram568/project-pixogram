package com.pixogram.actionservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionErrorResponse {
	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
}
