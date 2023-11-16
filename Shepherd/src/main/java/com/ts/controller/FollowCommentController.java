package com.ts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ts.model.FollowComment;
import com.ts.service.FollowCommentService;

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
