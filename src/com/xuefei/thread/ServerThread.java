package com.xuefei.thread;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.xuefei.util.NameSocket;
import com.xuefei.util.SendMessage;

public class ServerThread implements Runnable{
	Socket s=null;
	String name=null;
	ServerThread(Socket s,String name){
		this.s=s;
		this.name=name;
	}
			
	@Override
	public void run() {
		InputStream ips=null;
		try {	
			ips = s.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=ips.read(buf))!=-1){
				if(buf[0]==54){					
					String message=new String(buf,1,len-1);
					message=name+","+message;
					SendMessage.sendAllMessage(message);
				}
				if(buf[0]==55){
					String message=new String(buf,1,len-1);
					String[] split = message.split(",");							
					String Toname=split[0];				
					String primessage=split[1];
					SendMessage.sendPriMessage(name,Toname,primessage);	
				}
				if(buf[0]==56){
					String Toname=new String(buf,1,len-1);
					String[] split = Toname.split(",");
					String givename=split[0];
					String type=split[1];
					SendMessage.sendFileHeader(givename,name,type);
				}
			}			
		} catch (IOException e) {	
			System.out.println(s.getInetAddress().getHostAddress()+"下线");
			//移除Map集合对应对象
			NameSocket.removeMapByName(name);
			//向客户端发送用户下线信息
			SendMessage.sendRemoveUser(name);			
		} finally {
			try {
				ips.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}
