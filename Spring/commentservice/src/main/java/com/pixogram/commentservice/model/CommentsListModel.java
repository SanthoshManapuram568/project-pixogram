package com.pixogram.commentservice.model;

import java.util.List;

import com.pixogram.commentservice.entity.Comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsListModel {
	
	public List<Comments> commentslist;

}
