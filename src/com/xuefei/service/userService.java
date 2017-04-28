package com.xuefei.service;

import java.util.List;

import com.xuefei.entity.user;

public interface userService {
	public abstract List<user> findAll();
	public abstract boolean findUser(String name,String password);
}
