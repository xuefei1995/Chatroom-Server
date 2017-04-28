package com.xuefei.serviceImpl;

import java.util.List;

import com.xuefei.dao.userDao;
import com.xuefei.daoImpl.userDaoImpl;
import com.xuefei.entity.user;
import com.xuefei.service.userService;

public class userServiceImpl implements userService {
	userDao dao=new userDaoImpl();

	@Override
	public List<user> findAll() {
		List<user> list = dao.findAll();
		return list;
	}

	@Override
	public boolean findUser(String name, String password) {
		boolean flag=false;
		user us = dao.findUser(name, password);
		if(us!=null){
			flag=true;
		}
		return flag;
	}


}
