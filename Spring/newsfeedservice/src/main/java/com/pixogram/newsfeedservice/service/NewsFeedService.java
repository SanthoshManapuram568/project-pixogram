package com.pixogram.newsfeedservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pixogram.newsfeedservice.entity.NewsFeed;
import com.pixogram.newsfeedservice.model.NewsFeedModel;
import com.pixogram.newsfeedservice.repository.NewsfeedRepository;

@Component
public class NewsFeedService {
	@Autowired
	private NewsfeedRepository newsFeedRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<NewsFeedModel> findAllNewsFeed()
	{
		List<NewsFeed> feed1=this.newsFeedRepository.findAll();
		List<NewsFeedModel> feeds=feed1.stream().map(feed->{
			NewsFeedModel nm=new NewsFeedModel(feed.getUserId(),feed.getFeed(),feed.getCreatedOn().toString());
			return nm;
		}).collect(Collectors.toList());
		return feeds;
		
	}
	public List<NewsFeedModel> findNewsByUserId(Integer id)
	{
		List<NewsFeed> res=this.newsFeedRepository.findByUserId(id);
		List<NewsFeedModel> feeds=res.stream().map(feed->{
			NewsFeedModel nm=new NewsFeedModel(feed.getUserId(),feed.getFeed(),feed.getCreatedOn().toString());
			logger.info("feed result=====================>>>>>>"+nm);
			return nm;
		}).collect(Collectors.toList());
		logger.info("Feed=================>"+feeds );
		return feeds;
	}
	public NewsFeedModel findNewsFeedById(Integer id)
	{
		Optional<NewsFeed> record=this.newsFeedRepository.findById(id);
		NewsFeed newsFeed=null;
		if(record.isPresent())
		{
			newsFeed=record.get();
		}
		NewsFeedModel newsFeedModel=new NewsFeedModel();
		
		newsFeedModel.setFeed(newsFeed.getFeed());
		newsFeedModel.setCreatedOn(newsFeed.getCreatedOn().toString());
		return newsFeedModel;
	}
	public NewsFeedModel addAllNewsFeed(NewsFeedModel newsFeedModel)
	{
		NewsFeed newsFeed=new NewsFeed();
		
		newsFeed.setFeed(newsFeedModel.getFeed());
		newsFeed.setUserId(newsFeedModel.getUserId());
		
		this.newsFeedRepository.save(newsFeed);
		return newsFeedModel;
	}
	public NewsFeedModel updateNewsFeed(NewsFeedModel newsFeedModel)
	{
		NewsFeed newsFeed=new NewsFeed();
		
		newsFeed.setFeed(newsFeedModel.getFeed());
	
		this.newsFeedRepository.save(newsFeed);
		return newsFeedModel;
	}
	public NewsFeed deleteAllNewsFeed(Integer id)
	{
		Optional<NewsFeed> record=this.newsFeedRepository.findById(id);
		NewsFeed newsFeed=null;;
		if(record.isPresent())
		{
			newsFeed=record.get();
		}
		this.newsFeedRepository.deleteById(id);
		return newsFeed;
	}
}
