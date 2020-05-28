package com.pixogram.actionservice.services;

import java.util.List;
import java.util.Optional;

import com.pixogram.actionservice.entity.Action;
import com.pixogram.actionservice.model.CountOfActionsModel;

public interface IActionService {
	List<Action> findAllActions();
	Optional<Action> findActionById(Integer id);
	boolean addAction(Action action);
	boolean updateAction(Action action);
	public CountOfActionsModel getLikesByMediaId(Integer id);
}
