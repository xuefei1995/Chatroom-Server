package com.xuefei.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;












import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtil {
	private static BasicDataSource bds=null;
	static{
		InputStream in = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
		Properties pro = new Properties();
			try {
				pro.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}			
			try {
				bds=(BasicDataSource) BasicDataSourceFactory.createDataSource(pro);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}	
	public static Connection getConnect(){
		Connection conn =null;
		try {
			conn = bds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(PreparedStatement pst,Connection con){
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
	//жиди
	public static void close(ResultSet rst,PreparedStatement pst,Connection con){
		if(rst!=null){
			try {
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} 
}
