package com.littles.receivers;

import com.littles.datas.DataDone;
import com.littles.services.ServiceSendLocation;
import com.littles.utils.UtilBootCheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 网络连接的广播接收者
 */
public class ReceiverConnection extends BroadcastReceiver {
	
	public void onReceive(Context context, Intent intent) {  
		System.out.println("==================网络连接的广播接收者====================");
		boolean isStart = DataDone.readDoneStart(context);
		if(!isStart){
			
			return;
			
		}else{		
			
			//---如果当前sim卡的IMSI码与存储的IMSI码不一样
			if(UtilBootCheck.isIMSIDifferent(context)){
				
				Intent serviceIntent = new Intent(context, ServiceSendLocation.class);
		    	context.startService(serviceIntent);
		    	
			}

		}
	 
	}		
	
}