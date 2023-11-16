package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.FollowCommentRepository;
import com.ts.model.FollowComment;

@Service
public class FollowCommentService {

	@Autowired
	FollowCommentRepository fcr;

	public List<FollowComment> getFollowComments() {
		return fcr.findAll();
	}

}
