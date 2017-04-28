package com.xuefei.server;
import java.io.IOException;
import java.net.*;

import com.xuefei.thread.LoginThread;

public class ServerMain {
	public static void main(String[] args){
		ServerSocket ss=null;
		try {			
			ss=new ServerSocket(10000);
			while(true){
				Socket s=ss.accept();
				System.out.println(s.getInetAddress().getHostAddress()+"成功连接");
				new Thread(new LoginThread(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
