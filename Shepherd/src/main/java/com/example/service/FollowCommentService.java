package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FollowCommentRepository;
import com.example.model.FollowComment;

@Service
public class FollowCommentService {

	@Autowired
	FollowCommentRepository fcr;

	public List<FollowComment> getFollowComments() {
		return fcr.findAll();
	}

}
