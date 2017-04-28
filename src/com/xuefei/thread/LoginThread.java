package com.xuefei.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.xuefei.util.NameSocket;
import com.xuefei.util.SendMessage;
import com.xuefei.util.userExistCheck;

public class LoginThread implements Runnable {
	static boolean flag=false;
	public static String name=null;
	private Socket s=null;
	OutputStream ops=null;
	
	public LoginThread(Socket s){
		this.s=s;
	}
		
	@Override
	public void run() {
		while(true){			
			try {
				flag=userExistCheck.checkUser(s);//先判断用户是否存在
			} catch (Exception e1) {
				break; //用户下线结束线程
			}			
			if(flag==true){				
				//判断用户是否在线
				boolean tag=true;
				List<String> list = NameSocket.getKeyList();
				for (String listname : list) {
					if(listname.equals(name)){
						tag=false;
						break;
					}
				}
				
				if(tag==true){		
					NameSocket.addMap(name, s);
					System.out.println(name+"欢迎登陆");
					try {
						ops = s.getOutputStream();
						ops.write(1);
					} catch (IOException e) {
						e.printStackTrace();
					}									
					new Thread(new ServerThread(s,name)).start();					
					SendMessage.sendUser();
					flag=false;
					break;
				} else {
					try {
						ops = s.getOutputStream();
						ops.write(3);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else{
				System.out.println("用户名不存在");
				try {
					ops = s.getOutputStream();
					ops.write(2);
				} catch (IOException e) {
					break;
				} 
			}	
		}
		System.out.println("结束登陆判断线程");
	}
}
