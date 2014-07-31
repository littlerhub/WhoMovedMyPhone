package com.littles.receivers;

import com.littles.datas.DataDone;
import com.littles.listeners.ObserverOnSmsChange;
import com.littles.services.ServiceSendIMSI;
import com.littles.utils.UtilBootCheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

public class ReceiverBoot extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {

		System.out.println("===========开机完成了============1");
		boolean isStart = DataDone.readDoneStart(context);
		System.out.println("=================================2" + isStart);
		if(!isStart){
			
			return;
			
		}else{
			System.out.println("=================================3");
			
			String number = DataDone.readDoneNumber(context);

			System.out.println("=================================4");
			//---如果当前sim卡的IMSI码与存储的IMSI码不一样
			if(UtilBootCheck.isIMSIDifferent(context)){
				System.out.println("=================================5");
				//---给短信绑定监听器
						//当有指定号码的短信被发送或被接收时，会全部删掉该号码的所有短信记录
				Uri smsUri = Uri.parse("content://sms");  
				context.getContentResolver().registerContentObserver(smsUri, 
		        		true, new ObserverOnSmsChange(new Handler(), number, context)); 
				System.out.println("=================================6");			
				
				//---将当前手机的IMSI码发短信给目标号码
				Intent imsiIntent = new Intent(context, ServiceSendIMSI.class);
				context.startService(imsiIntent);
				System.out.println("=================================7");
			
			}

		}

	}

}
