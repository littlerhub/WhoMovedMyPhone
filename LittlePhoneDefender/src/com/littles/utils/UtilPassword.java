package com.littles.utils;

public class UtilPassword {

	public static int isOk(String first, String confirm){
		
		int result = 0;
		
		if(UtilString.isEmpty(first) || UtilString.isEmpty(confirm)){
			
			result = 0;
			
		}else if(first.equals(confirm)){
			
			result = 1;
			
		}else if(!first.equals(confirm)){
			
			result = -1;
			
		}
		
		return result;
		
	}
	
}
