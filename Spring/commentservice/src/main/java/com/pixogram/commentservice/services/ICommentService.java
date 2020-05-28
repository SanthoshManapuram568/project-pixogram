package com.pixogram.commentservice.services;

import java.util.List;
import java.util.Optional;

import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.model.CommentsDataModel;
import com.pixogram.commentservice.model.CommentsNumberModel;

public interface ICommentService {
	public List<Comments> getall();
	public void save(CommentsDataModel comment);
	public Optional<Comments> getWithId(Integer id);
	public void updateuser(CommentsDataModel action);
	public CommentsNumberModel getCountById(Integer mediaid);

}