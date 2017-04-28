package com.xuefei.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;


public class SendMessage {
	//发送在线用户
	public static void sendUser(){
		List<Socket> list=NameSocket.getValueList();
		String namelist ="";
		for (Socket socket : list) {
			try {
				OutputStream ops = socket.getOutputStream();
				List<String> listname = NameSocket.getKeyList();
				for (String name : listname) {
					namelist+=name+",";
				}
				namelist=namelist.substring(0, namelist.length()-1);//去掉末尾逗号
				ops.write((4+namelist).getBytes());
				namelist="";//给一个用户发送完后，清空该字符串
				System.out.println("发送了在线用户");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	//发送群聊数据方法
	public static void sendAllMessage(String message){
		List<Socket> list=NameSocket.getValueList();
		for (Socket socket : list) {
			try {
				OutputStream ops = socket.getOutputStream();
				ops.write((6+message).getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//发送用户下线数据方法
	public static void sendRemoveUser(String removename){
		List<Socket> list=NameSocket.getValueList();
		for (Socket socket : list) {
			try {
				OutputStream ops = socket.getOutputStream();
				ops.write( (5+removename+",").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//发送私聊数据方法	
	public static void sendPriMessage(String name,String Toname,String primessage){
		Socket findsocket = NameSocket.findSocketByName(Toname);
		try {
			OutputStream ops = findsocket.getOutputStream();
			ops.write( (7+name+","+primessage).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//发生文件头
	public static void sendFileHeader(String Toname,String name,String type){
		Socket findsocket = NameSocket.findSocketByName(Toname);
		Socket mysocket = NameSocket.findSocketByName(name);
		try {
			OutputStream ops = findsocket.getOutputStream();
			ops.write( (8+name+","+type).getBytes());
			String ip=findsocket.getLocalAddress().getHostAddress();
			String port="20000";
			OutputStream myops = mysocket.getOutputStream();
			myops.write((9+ip+","+port).getBytes());
		} catch (IOException e) {
			System.out.println("接收数据用户已经下线");
		}
	}

	
	
}
