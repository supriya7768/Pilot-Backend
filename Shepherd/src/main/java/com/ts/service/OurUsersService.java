package com.ts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.dao.OurUsersRepository;
import com.ts.model.OurUsers;

@Service
public class OurUsersService {

	@Autowired
	private OurUsersRepository ourUserRepository;

	public OurUsers saveUser(OurUsers ourUser) {
		return ourUserRepository.save(ourUser);
	}

	public Object findAllUsers() {
		return ourUserRepository.findAll();
	}

	public Object findByEmail(String username) {
		return ourUserRepository.findByEmail(username);
	}

}
