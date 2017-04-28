package com.xuefei.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuefei.dao.userDao;
import com.xuefei.entity.user;
import com.xuefei.util.jdbcUtil;

public class userDaoImpl implements userDao {

	@Override
	public List<user> findAll() {
		Connection con = jdbcUtil.getConnect();
		List<user> list=new ArrayList<user>();
		PreparedStatement psm = null;
		ResultSet rst =null;
		String sql="SELECT * FROM USER";
		try {
			psm = con.prepareStatement(sql);
			rst = psm.executeQuery();
			while(rst.next()){
				int id=rst.getInt("id");
				String name=rst.getString("name");
				String password=rst.getString("password");
				user u=new user();
				u.setId(id);
				u.setName(name);
				u.setPassword(password);
				list.add(u);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rst, psm, con);
		}
		return list;		
	}

	@Override
	public user findUser(String name, String password) {
		Connection con = jdbcUtil.getConnect();
		PreparedStatement psm = null;
		ResultSet rst =null;
		String sql="SELECT * FROM USER WHERE NAME=? AND PASSWORD=?";
		user u=null;
		try {
			psm=con.prepareStatement(sql);
			psm.setString(1, name);
			psm.setString(2,password);
			rst=psm.executeQuery();
			if(rst.next()){
				int id=rst.getInt("id");
				String username=rst.getString("name");
				String userpassword=rst.getString("password");
				u=new user();
				u.setId(id);
				u.setName(username);
				u.setPassword(userpassword);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(rst,psm,con);
		}
		return u;
	}
}
