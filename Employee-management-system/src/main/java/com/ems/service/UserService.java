package com.ems.service;

import com.ems.entity.User;

public interface UserService {

	User login(String userName, String password);
}
