package com.pixogram.blockservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.blockservice.entity.BlockedUser;
import com.pixogram.blockservice.model.BlockData;
import com.pixogram.blockservice.repository.BlockedRepository;

@RestController
public class BlockedController {
		@Autowired
		private BlockedRepository blockedRepository;
		
		@GetMapping("/blocked")
		public ResponseEntity<BlockData> getBlockedDetails()
		{
			List<BlockedUser> details=this.blockedRepository.findAll();
			BlockData blockData = new BlockData();
			blockData.setBlockUser(details);
			ResponseEntity<BlockData> response =new  ResponseEntity<BlockData>(blockData,HttpStatus.OK);
			return response;
		}
}
