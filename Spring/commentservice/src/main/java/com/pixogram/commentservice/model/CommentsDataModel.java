package com.pixogram.commentservice.model;

import java.time.LocalDateTime;
import java.util.List;

import com.pixogram.commentservice.entity.Comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDataModel {
	
	private Integer id;
	private Integer mediaId;
	private Integer userId;
	private String comments;
	private LocalDateTime createdOn;
}
