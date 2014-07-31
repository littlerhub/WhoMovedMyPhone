package com.littles.utils;

public class UtilString {
	
	public static boolean isEmpty(String password){
		
		boolean flag = false;
		
		if(password == null || "".equals(password)){
			
			flag = true;
			
		}
		
		return flag;
		
	}
	
}
