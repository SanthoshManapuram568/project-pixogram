package com.pixogram.mediaplumbingservice.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pixogram.mediaplumbingservice.model.CommentsListModel;
import com.pixogram.mediaplumbingservice.model.CommentsNumberModel;



@FeignClient(name ="api-gateway", url = "http://localhost:8765/")
@RibbonClient(name ="comment-service")

public interface CommentServiceProxy {
	
	@GetMapping("/comment-service/getcount/{mediaid}")
	public ResponseEntity<CommentsNumberModel> getCountById(@PathVariable Integer mediaid);

	//@GetMapping("/comment-service/commentbymediaid/{mediaId}")
	//public ResponseEntity<CommentsListModel> getCommentsById(@PathVariable Integer mediaId);

}
