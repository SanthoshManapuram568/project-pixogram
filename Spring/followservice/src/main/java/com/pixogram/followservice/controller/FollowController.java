package com.pixogram.followservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.followservice.entity.Follow;
import com.pixogram.followservice.model.FollowData;
import com.pixogram.followservice.model.FollowModel;
import com.pixogram.followservice.model.NewsFeedModel;
import com.pixogram.followservice.repository.FollowRepository;


@RestController
public class FollowController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FollowRepository followRepository;
	
	@GetMapping("/check-followings/mine/{mineId}/other/{otherId}")
	public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer mineId, @PathVariable Integer otherId){
		Boolean status = this.followRepository.checkFollowing(mineId, otherId);
		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(status,HttpStatus.OK);
		return result;
		
	}
	
	@PostMapping("/follow")
	public Boolean follow(@RequestBody Follow follow) {
		Boolean status  = this.followRepository.follow(follow);
		/*
		 NewsFeedModel feedModel = new NewsFeedModel();
		feedModel.setFeed("You are Following"+follow.getUserId());
		feedModel.setUserId(follow.getFollowerId());
		*/
		return status;
	}
	
	@DeleteMapping("/unfollow/mine/{mineId}/other/{otherId}")
	public Boolean unfollow(@PathVariable Integer mineId , @PathVariable Integer otherId) {
		logger.info(mineId+"--------"+otherId);
		Boolean status = this.followRepository.unFollow(mineId, otherId);
		
		/*
		
		NewsFeedModel feedModel = new NewsFeedModel();
		feedModel.setFeed("You are Following"+otherId);
		logger.info("==========>"+feedModel.getFeed());
		feedModel.setUserId(mineId);
		
		*/
		return status;
	}
}
