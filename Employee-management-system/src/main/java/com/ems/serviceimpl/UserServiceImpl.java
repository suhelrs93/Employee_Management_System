package com.ems.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.User;
import com.ems.repository.UserRepository;
import com.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User login(String userName, String password) {
		
		return userRepository.findByUserNameAndPassword(userName, password);
	}

}
