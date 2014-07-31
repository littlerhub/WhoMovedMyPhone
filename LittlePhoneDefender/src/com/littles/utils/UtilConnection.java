package com.littles.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

public class UtilConnection {

	/**
	 * ȡ���û��ֻ���ǰ����������״̬
	 * @param context
	 * @return boolean
	 */
	public static boolean isConnected(Context context){
		
		boolean flag = false;
		
		//---����������ӷ���   
	    ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
	    
	    //---��ȡWIFI��������״̬  
	    State wifiState = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();   	 
	    //---��ȡGPRS��������״̬   
	    State gprsState = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
	    
	    //---Wifi��GPRSֻҪ��һ�������ͷ���true
	    if(State.CONNECTED == gprsState || State.CONNECTED == wifiState){
	    	
	    	flag = true;
	    	
	    }
	    
	    return flag;
		
		
	}
	
}
