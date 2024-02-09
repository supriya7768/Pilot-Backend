package com.ts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.dao.LoginRepository;
import com.ts.model.Login;
import com.ts.model.OurUsers;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private OurUsersService ourUsersService;

	public Login saveLogin(Login login) {
		return loginRepository.save(login);
	}

	public List<Login> findAllUsers() {
		return loginRepository.findAll();
	}

	public Login findLoginByEmail(String email) {
		return loginRepository.findByEmail(email);
	}

	public void updateLoginDetails(Login login) {
		loginRepository.save(login);
	}

	public Login findLoginByEmailAndPassword(String email, String password) {
		return loginRepository.findByEmailAndPassword(email, password);
	}

	@Transactional
	public void updatePasswordInOurUsers(String email, String encryptedPassword) {
		OurUsers user = ourUsersService.findUserByEmail(email);
		if (user != null) {
			user.setPassword(encryptedPassword);
			ourUsersService.updateUserDetails(user); // Update password in OurUsers table
		}
	}
}
