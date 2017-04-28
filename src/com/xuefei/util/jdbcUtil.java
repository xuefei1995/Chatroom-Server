package com.xuefei.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtil {
	private static String url=null;
	private static String user=null;
	private static String password=null;
	private static String driverclass=null;
	
	static{
		try {			
			InputStream in = jdbcUtil.class.getResourceAsStream("/jdbc.properties");
			Properties pro = new Properties();
				pro.load(in);
				url=pro.getProperty("url");
				user=pro.getProperty("user");
				password=pro.getProperty("password");
				driverclass=pro.getProperty("driverclass");
			Class.forName(driverclass);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static Connection getConnect(){
		Connection con=null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
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
