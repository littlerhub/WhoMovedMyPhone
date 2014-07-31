package com.littles.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class UtilConnection {

	/**
	 * 取得用户手机当前的网络连接状态
	 * @param context
	 * @return boolean
	 */
	public static boolean isConnected(Context context){
		
		boolean flag = false;
		
		//---获得网络连接服务   
	    ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
	    
	    //---获取WIFI网络连接状态  
	    State wifiState = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();   	 
	    //---获取GPRS网络连接状态   
	    State gprsState = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
	    
	    //---Wifi和GPRS只要有一个开启就返回true
	    if(State.CONNECTED == gprsState || State.CONNECTED == wifiState){
	    	
	    	flag = true;
	    	
	    }
	    
	    return flag;
		
		
	}
	
}
