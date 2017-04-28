package com.xuefei.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.xuefei.service.userService;
import com.xuefei.serviceImpl.userServiceImpl;
import com.xuefei.thread.LoginThread;

public class userExistCheck {
	static boolean flag=false;
	static userService usv=new userServiceImpl();
	static InputStream ips=null;
	public static boolean checkUser(Socket s) throws Exception{
		try {
			ips = s.getInputStream();
			byte[] b=new byte[1024];
				int len=0;
			while((len=ips.read(b))!=-1){
				String message=new String(b,0,len);
				String[] spt = message.split(",");
				String name=spt[0];
				String password=spt[1];
				flag=usv.findUser(name, password);
					if(flag==true) {
						LoginThread.name=name;
					}
				break;
			}
		} catch (IOException e) {
			throw new Exception("用户下线");
		} 
		return flag;
	}
}
