package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FollowComment;
import com.example.service.FollowCommentService;

@CrossOrigin("*")
@RestController
public class FollowCommentController {

	@Autowired
	FollowCommentService fcs;
	
	@GetMapping("/get-follow-comment")
	@ResponseBody
	public List<FollowComment> getFollowComments() {
		List<FollowComment> fc = fcs.getFollowComments();
		return fc;
	}
}
