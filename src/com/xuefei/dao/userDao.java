package com.xuefei.dao;

import java.util.List;
import com.xuefei.entity.user;

public interface userDao {
	public abstract List<user> findAll();
	public abstract user findUser(String name,String password);
}
