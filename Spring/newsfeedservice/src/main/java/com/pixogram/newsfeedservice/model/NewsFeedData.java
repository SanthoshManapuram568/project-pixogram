package com.pixogram.newsfeedservice.model;

import java.util.List;

import com.pixogram.newsfeedservice.entity.NewsFeed;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsFeedData {

	List<NewsFeed> newsFeeds;
}
