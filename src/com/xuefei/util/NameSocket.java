package com.xuefei.util;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class NameSocket {
	private static TreeMap<String,Socket> tm=new TreeMap<String,Socket>();
	
	public static void addMap(String name,Socket s){
		tm.put(name, s);
	}
	
	public static Socket findSocketByName(String name){
		Socket s=null;
		Set<Entry<String, Socket>> entryset = tm.entrySet();
		for (Entry<String, Socket> entry : entryset) {
			if((entry.getKey()).equals(name)){
				s=entry.getValue();
				break;
			}
		}
		return s;
	}
	
	public static void removeMapByName(String name){
		tm.remove(name);
	}
	
	public static List<Socket> getValueList(){
		List<Socket> list=new ArrayList<Socket>();
		Set<Entry<String, Socket>> entryset = tm.entrySet();
		for (Entry<String, Socket> entry : entryset) {
				list.add(entry.getValue());
		}
		return list;
	}
	
	public static List<String> getKeyList(){
		List<String> list=new ArrayList<String>();
		Set<Entry<String, Socket>> entryset = tm.entrySet();
		for (Entry<String, Socket> entry : entryset) {
				list.add(entry.getKey());
		}
		return list;
	}
}
