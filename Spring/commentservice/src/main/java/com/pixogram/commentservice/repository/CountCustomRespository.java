package com.pixogram.commentservice.repository;

import com.pixogram.commentservice.model.CommentsNumberModel;

public interface CountCustomRespository {
	
	CommentsNumberModel findCountById(Integer mediaid);

}
