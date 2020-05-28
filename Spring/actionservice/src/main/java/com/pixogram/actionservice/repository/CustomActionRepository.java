package com.pixogram.actionservice.repository;

import com.pixogram.actionservice.model.CountOfActionsModel;

public interface CustomActionRepository {

	public CountOfActionsModel getLikesAndDislikes(Integer mediaId);
	
}
