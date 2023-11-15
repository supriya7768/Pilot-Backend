package com.ts.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.FollowComment;

@Repository
public interface FollowCommentRepository extends JpaRepository<FollowComment, Long> {
    // Add custom query methods if needed
	
}
