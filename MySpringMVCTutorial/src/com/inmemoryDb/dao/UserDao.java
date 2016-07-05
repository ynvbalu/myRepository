package com.inmemoryDb.dao;

import java.util.List;

import com.inmemoryDb.model.User;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}