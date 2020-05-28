package com.pixogram.commentservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.model.CommentsDataModel;
import com.pixogram.commentservice.model.CommentsNumberModel;
import com.pixogram.commentservice.repository.CommentsRepository;
import com.pixogram.commentservice.repository.CountCustomRespository;
@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired 
	private CountCustomRespository countCustomRepository;

	@Override
	public List<Comments> getall() {
		List<Comments> records = this.commentsRepository.findAll();
		return records;
	}

	@Override
	public void save(CommentsDataModel comment) {
Comments data = new Comments();
		
		data.setComments(comment.getComments());
		data.setUserId(comment.getUserId());
		data.setMediaId(comment.getMediaId());
		
		this.commentsRepository.save(data);
		
		
	}

	@Override
	public Optional<Comments> getWithId(Integer id) {
		Optional<Comments> record= this.commentsRepository.findById(id);
		return record;
		
	}

	@Override
	public void updateuser(CommentsDataModel comment) {
		Comments data = new Comments();
		data.setId(comment.getId());
		data.setComments(comment.getComments());
		data.setUserId(comment.getUserId());
		data.setMediaId(comment.getMediaId());
		data.setCreatedOn(comment.getCreatedOn());
		this.commentsRepository.save(data);
	}

	@Override
	public CommentsNumberModel getCountById(Integer mediaid) {
		
		CommentsNumberModel commentsNumberModel =  this.countCustomRepository.findCountById(mediaid);
		return commentsNumberModel;
	} 


	

}
