package com.pixogram.actionservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pixogram.actionservice.entity.Action;
import com.pixogram.actionservice.exceptions.ActionErrorResponse;
import com.pixogram.actionservice.exceptions.ActionNotFoundException;
import com.pixogram.actionservice.model.ActionListModel;
import com.pixogram.actionservice.model.CountOfActionsModel;
import com.pixogram.actionservice.services.IActionService;

@RestController
@CrossOrigin("localhost:4200")
public class ActionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	//private ActionRepository actionRepository;
	private IActionService actionService;
	
	@GetMapping("/getlikes/{id}")
	public ResponseEntity<CountOfActionsModel> getLikes(@PathVariable Integer id) {
		CountOfActionsModel count = this.actionService.getLikesByMediaId(id);
		ResponseEntity<CountOfActionsModel> result = new ResponseEntity<CountOfActionsModel>(count,HttpStatus.OK);
		logger.info("count in controller :"+count);
		return result;
		
	}
	
	@GetMapping("/actions")
	public ResponseEntity<ActionListModel> getActionDetails()
	{
		List<Action> details=this.actionService.findAllActions();
		if(details.size()==0)
			throw new ActionNotFoundException("No actions in db");
		ActionListModel actionData=new ActionListModel();
		actionData.setActions(details);
		ResponseEntity<ActionListModel> response= new ResponseEntity<ActionListModel>(actionData,HttpStatus.OK);
		return response;
	}
	@GetMapping("/actions/{id}")
	public ResponseEntity<Action> getActionById(@PathVariable Integer id){
		Optional<Action> record=this.actionService.findActionById(id);
		
		Action action=null;
		if(record.isPresent())
		{
			action=record.get();
		}
		else {
			throw new ActionNotFoundException("Action with id-" + id + " not Found");
		}
		this.actionService.findActionById(id);
		ResponseEntity<Action> response=new ResponseEntity<Action>(action,HttpStatus.OK);
		
		return response;
	
	}
	@PostMapping("/actions")
	public ResponseEntity<Action> addActions(@RequestBody Action action)
	{
		//this.actionService.updateAction(action);
		if(!this.actionService.addAction(action))
			throw new RuntimeException("Could not add new record!!!");
		ResponseEntity<Action> response =new ResponseEntity<Action>(action,HttpStatus.OK);
		return response;
		
	}
	@PutMapping("/actions")
	public ResponseEntity<Action> updateAction(@RequestBody Action action)
	{
		
		if(!this.actionService.updateAction(action))
			throw new RuntimeException("Could not update record!!!");
		ResponseEntity<Action> response=new ResponseEntity<Action>(action,HttpStatus.OK);
		
		return response;
	}
	@ExceptionHandler  // ~catch
	public ResponseEntity<ActionErrorResponse> actionNotFoundHandler(ActionNotFoundException ex) {
		// create error object
		ActionErrorResponse error = new ActionErrorResponse(ex.getMessage(), 
															  HttpStatus.NOT_FOUND.value(), 
															  System.currentTimeMillis());
		ResponseEntity<ActionErrorResponse> response =
										new ResponseEntity<ActionErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<ActionErrorResponse> actionOperationErrorHAndler(Exception ex) {
		// create error object
		ActionErrorResponse error = new ActionErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<ActionErrorResponse> response =
										new ResponseEntity<ActionErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
}
}
