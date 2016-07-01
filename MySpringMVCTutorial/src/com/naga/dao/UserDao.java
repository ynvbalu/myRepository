package com.naga.dao;

import java.util.List;

import com.naga.model.User;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}