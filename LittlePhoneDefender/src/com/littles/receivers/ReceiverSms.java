package com.littles.receivers;

import com.littles.utils.UtilService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
public class ReceiverSms extends BroadcastReceiver {
	
	public void onReceive(Context context, Intent intent) {  
		try{
			// �ر����з���
			UtilService.closeServices(context);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}		
	
}