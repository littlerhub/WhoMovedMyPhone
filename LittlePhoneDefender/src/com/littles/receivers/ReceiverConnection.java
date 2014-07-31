package com.littles.receivers;

import com.littles.datas.DataDone;
import com.littles.services.ServiceSendLocation;
import com.littles.utils.UtilBootCheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * �������ӵĹ㲥������
 */
public class ReceiverConnection extends BroadcastReceiver {
	
	public void onReceive(Context context, Intent intent) {  
		System.out.println("==================�������ӵĹ㲥������====================");
		boolean isStart = DataDone.readDoneStart(context);
		if(!isStart){
			
			return;
			
		}else{		
			
			//---�����ǰsim����IMSI����洢��IMSI�벻һ��
			if(UtilBootCheck.isIMSIDifferent(context)){
				
				Intent serviceIntent = new Intent(context, ServiceSendLocation.class);
		    	context.startService(serviceIntent);
		    	
			}

		}
	 
	}		
	
}