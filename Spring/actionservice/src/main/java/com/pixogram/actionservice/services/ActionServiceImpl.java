package com.pixogram.actionservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.actionservice.entity.Action;
import com.pixogram.actionservice.model.CountOfActionsModel;
import com.pixogram.actionservice.repository.ActionRepository;
import com.pixogram.actionservice.repository.CustomActionRepository;
@Service
public class ActionServiceImpl implements IActionService {
	
	@Autowired
	private ActionRepository actionRepository;
	
	@Autowired
	private CustomActionRepository customActionRepository;
	
	@Override
	public List<Action> findAllActions() {
		// TODO Auto-generated method stub
		return this.actionRepository.findAll();
		//return null;
	}

	@Override
	public Optional<Action> findActionById(Integer id) {
		// TODO Auto-generated method stub
		//Action action = new Action();
		Optional<Action> action = this.actionRepository.findById(id);
		return action;
		//return null;
	}

	@Override
	public boolean updateAction(Action action) {
		// TODO Auto-generated method stub
		//return this.actionRepository.save(action);
		this.actionRepository.save(action);
		return false;
	}

	@Override
	public boolean addAction(Action action) {
		// TODO Auto-generated method stub
		//return this.actionRepository.save(action);
		this.actionRepository.save(action);
		return false;
	}

	@Override
	public CountOfActionsModel getLikesByMediaId(Integer id) {
	
		CountOfActionsModel count = this.customActionRepository.getLikesAndDislikes(id);
		return count;
	}

}
