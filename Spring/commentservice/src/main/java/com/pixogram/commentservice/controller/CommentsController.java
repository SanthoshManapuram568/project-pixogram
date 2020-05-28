package com.pixogram.commentservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.exceptions.CommentErrorResponse;
import com.pixogram.commentservice.exceptions.CommentNotFoundException;
import com.pixogram.commentservice.model.CommentsDataModel;
import com.pixogram.commentservice.model.CommentsListModel;
import com.pixogram.commentservice.model.CommentsNumberModel;
import com.pixogram.commentservice.services.ICommentService;


@RestController
public class CommentsController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICommentService commentService;
	
	
	
	@GetMapping("/comments")
	public ResponseEntity<CommentsListModel> getallcomments(){
	
		CommentsListModel result = new CommentsListModel();
		result.setCommentslist(this.commentService.getall());
		ResponseEntity<CommentsListModel> data = new ResponseEntity<CommentsListModel>(result, HttpStatus.OK);
		
		return data;
	}
	
	@PostMapping("/comment")
	public boolean save(@RequestBody CommentsDataModel comment) {
		this.commentService.save(comment);
		return true;
	}

	@GetMapping("/comment/{commentId}")
	public ResponseEntity<CommentsDataModel> getById(@PathVariable Integer commentId){
		CommentsDataModel data = new CommentsDataModel();
		Comments record = new Comments();
		Optional<Comments> comment = this.commentService.getWithId(commentId);
		if(comment.isPresent())
			record = comment.get();
		else {
			throw new CommentNotFoundException("comment not found");
		}
		data.setComments(record.getComments());
		data.setId(record.getId());
		data.setCreatedOn(record.getCreatedOn());
		data.setUserId(record.getUserId());
		data.setMediaId(record.getMediaId());
		ResponseEntity<CommentsDataModel> result = new ResponseEntity<CommentsDataModel>(data, HttpStatus.OK);
		return result;
	}
	
	
	@PutMapping("/comment")
	public boolean update(@RequestBody CommentsDataModel user) {
		
		this.commentService.updateuser(user);
		return true;
		
	}
	
	@GetMapping("/getcount/{mediaid}")
	public ResponseEntity<CommentsNumberModel> getCountById(@PathVariable Integer mediaid) {
		
		CommentsNumberModel data = this.commentService.getCountById(mediaid);
		
		ResponseEntity<CommentsNumberModel> result = 
									new ResponseEntity<CommentsNumberModel>(data, HttpStatus.OK);
		logger.info("result in comment controller : "+data);
		return result;
		
	}
	
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<CommentErrorResponse> commentNotFoundHandler(CommentNotFoundException ex) {
		// create error object
		CommentErrorResponse error = new CommentErrorResponse(ex.getMessage(), 
															  HttpStatus.NOT_FOUND.value(), 
															  System.currentTimeMillis());
		ResponseEntity<CommentErrorResponse> response =
										new ResponseEntity<CommentErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<CommentErrorResponse> commentOperationErrorHAndler(Exception ex) {
		// create error object
		CommentErrorResponse error = new CommentErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<CommentErrorResponse> response =
										new ResponseEntity<CommentErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
	
	
	
}
