package com.pixogram.mediaplumbingservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pixogram.mediaplumbingservice.model.NewsFeedModel;


@FeignClient(name ="api-gateway", url = "http://localhost:8765/")
@RibbonClient(name ="newsfeed-service")
public interface NewsFeedServiceProxy {
	
	@PostMapping("/newsfeed-service/newsfeed")
	public ResponseEntity<NewsFeedModel> addNewsFeed(@RequestBody NewsFeedModel newsFeed);
	
}
