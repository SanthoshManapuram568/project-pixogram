package com.pixogram.mediaplumbingservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.mediaplumbingservice.feignproxy.ActionServiceProxy;
import com.pixogram.mediaplumbingservice.feignproxy.CommentServiceProxy;
import com.pixogram.mediaplumbingservice.feignproxy.MediaServiceProxy;
import com.pixogram.mediaplumbingservice.feignproxy.NewsFeedServiceProxy;
import com.pixogram.mediaplumbingservice.model.CommentsListModel;
import com.pixogram.mediaplumbingservice.model.CommentsNumberModel;
import com.pixogram.mediaplumbingservice.model.MediaData;
import com.pixogram.mediaplumbingservice.model.MediaDataModel;
import com.pixogram.mediaplumbingservice.model.MediaDetails;
import com.pixogram.mediaplumbingservice.model.MediaDetailsModel;
import com.pixogram.mediaplumbingservice.model.MediaModel;
import com.pixogram.mediaplumbingservice.model.NewsFeedModel;
import com.pixogram.mediaplumbingservice.response.GalleryDisplayResponse;





@RestController
public class MediaServicePlumbingController {
	
	@Autowired
	private MediaServiceProxy mediaServiceProxy;
	@Autowired
	private CommentServiceProxy commentServiceProxy;
	@Autowired
	private ActionServiceProxy actionServiceProxy;
	@Autowired
	private NewsFeedServiceProxy feedServiceProxy;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping("/media")
	public void post(@RequestParam("file") MultipartFile file,
					@RequestParam("url") String url,
					@RequestParam("title") String title,
					@RequestParam("description") String description,
					@RequestParam("tags") String tags,
					@RequestParam("userid") String userid,
					@RequestParam("type") String type)
	{
		
		MediaDataModel model = new MediaDataModel(Integer.parseInt(userid),url,title,description,tags,type);
		
/*		NewsFeedModel feedModel = new NewsFeedModel();
		feedModel.setFeed("Media uploaded");
		feedModel.setUserId(Integer.parseInt(userid));
	
		this.feedServiceProxy.addNewsFeed(feedModel);
		*/
		this.mediaServiceProxy.saveData(model);
		this.mediaServiceProxy.save(file);
	
	}
	
	@GetMapping("/media/{userId}")
	public ResponseEntity<MediaDetailsModel> getAllById(@PathVariable Integer userId){
		
		ResponseEntity<MediaModel> media = this.mediaServiceProxy.getall(userId);
		
		List<GalleryDisplayResponse> filelist = new ArrayList<GalleryDisplayResponse>();
		
		logger.info("user id : => : "+userId);
		logger.info("List : " + media.getBody().getMedialist());
		for(MediaData data : media.getBody().getMedialist()) {	
			logger.info("ID :" + data.getId());
			
			CommentsNumberModel commentsCount =  this.commentServiceProxy.getCountById(data.getId()).getBody();
			
			Integer cmts = 0;
			if(commentsCount !=null) {
				cmts = commentsCount.getComments();
			}
			Integer likes = this.actionServiceProxy.getLikesAndDislikes(data.getId()).getBody().getLikes();
			Integer disLikes = this.actionServiceProxy.getLikesAndDislikes(data.getId()).getBody().getDisLikes();
			//CommentNumberModel commentNumberModel = this.commentServiceProxy.getCountById(data.getId()).getBody();
			//System.out.println(commentNumberModel.getComments());
			
			GalleryDisplayResponse response = new GalleryDisplayResponse(data.getId(), data.getUserId(), data.getTitle(), data.getDescription(), data.getTags(), data.getType(), data.getUrl(),cmts,likes,disLikes);
			filelist.add(response);
		}
		
		MediaDetailsModel resultlist = new MediaDetailsModel();
		resultlist.setFilelist(filelist);
		ResponseEntity<MediaDetailsModel> result = new ResponseEntity<MediaDetailsModel>(resultlist,HttpStatus.OK);	
		
		return result;
	}
	
	//comments getting problem
	
	@GetMapping("/mediadetails/{mediaId}")
	public ResponseEntity<MediaDetails> getMedialDetailsById(@PathVariable Integer mediaId){
		MediaData media = this.mediaServiceProxy.getDetailsBymediaId(mediaId).getBody();
		Integer likes = this.actionServiceProxy.getLikesAndDislikes(media.getId()).getBody().getLikes();
		Integer unlikes = this.actionServiceProxy.getLikesAndDislikes(media.getId()).getBody().getDisLikes();
		//Integer count = this.commentServiceProxy.getCountById(media.getId()).getBody().getComments();
		//logger.info("Count ======>>>>>>    "+count);
		
		/*CommentsListModel comments = new CommentsListModel();
		if(count == 0) {
			comments.setCommentsList(null);
		}	
		else {
				comments = this.commentServiceProxy.getCommentsById(media.getId()).getBody();
		}*/
		MediaDetails data = new MediaDetails(media.getId(), media.getUserId(), media.getTitle(), media.getDescription(), media.getTags(), media.getType(), media.getUrl(), likes, unlikes);
		ResponseEntity<MediaDetails> result = new ResponseEntity<MediaDetails>(data,HttpStatus.OK);	
		
		return result;
	}
	
	@GetMapping("/comment/{id}")
	public ResponseEntity<CommentsNumberModel> getCommentsCount(@PathVariable Integer id){
		ResponseEntity<CommentsNumberModel> count = this.commentServiceProxy.getCountById(id);
		return count;
	}
}

