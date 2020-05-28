package com.pixogram.newsfeedservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.newsfeedservice.entity.NewsFeed;
import com.pixogram.newsfeedservice.model.NewsFeedData;
import com.pixogram.newsfeedservice.model.NewsFeedModel;
import com.pixogram.newsfeedservice.service.NewsFeedService;

@CrossOrigin("*")
@RestController
public class NewsFeedController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NewsFeedService newsFeedService;
	@GetMapping("/newsfeeds")
	public ResponseEntity<List<NewsFeedModel>> getNewsFeeds()
	{
		List<NewsFeedModel> details=this.newsFeedService.findAllNewsFeed();
		ResponseEntity<List<NewsFeedModel>> response=new ResponseEntity<List<NewsFeedModel>>(details,HttpStatus.OK);
		return response;
	}
	@GetMapping("/newsfeeds/{userId}")
	public ResponseEntity<List<NewsFeedModel>> findFeedById(@PathVariable Integer userId)
	{
		List<NewsFeedModel> feed=this.newsFeedService.findNewsByUserId(userId);
		ResponseEntity<List<NewsFeedModel>> response=new ResponseEntity<List<NewsFeedModel>>(feed,HttpStatus.OK);
		return response;
	}
	@PutMapping("/newsfeeds")
	public ResponseEntity<NewsFeedModel> saveUpdate(@RequestBody NewsFeedModel newsFeed)
	{
		NewsFeedModel newsFeedModel=this.newsFeedService.updateNewsFeed(newsFeed);
		ResponseEntity<NewsFeedModel> response=new ResponseEntity<NewsFeedModel>(newsFeedModel,HttpStatus.OK);
		return response;
	}
	@PostMapping("/newsfeed")
	public ResponseEntity<NewsFeedModel> addNewsFeed(@RequestBody NewsFeedModel newsFeed)
	{
		NewsFeedModel newsFeeds=this.newsFeedService.addAllNewsFeed(newsFeed);
		ResponseEntity<NewsFeedModel> response =new ResponseEntity<NewsFeedModel>(newsFeeds,HttpStatus.OK);
		return response;
	}
	@DeleteMapping("newsfeeds/{newsFeedId}")
	public ResponseEntity<NewsFeed> deleteNewsFeed(@PathVariable Integer newsFeedId)
	{
		NewsFeed newsFeed=this.newsFeedService.deleteAllNewsFeed( newsFeedId);
		 ResponseEntity<NewsFeed> response =new  ResponseEntity<NewsFeed>(newsFeed,HttpStatus.OK);
		return response;
	}
	
}
