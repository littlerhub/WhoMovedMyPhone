package com.littles.receivers;

import com.littles.activitys.ActivityDoing;
import com.littles.datas.DataApplication;
import com.littles.utils.UtilService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
/**
 * 只在来电状态下，关闭服务
 * @author LittleBoy
 * 2013-04-13 20:15
 */
public class ReceiverCall extends BroadcastReceiver {
	
	private static int whitch = -1;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals("android.intent.action.PHONE_STATE")){		
			//设置一个监听器，监听电话的状态
			TelephonyManager manager = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
			
			switch(manager.getCallState()){
				// 来电
				case TelephonyManager.CALL_STATE_RINGING:{
					// 关闭所有服务
					whitch = UtilService.closeServices(context);
					System.out.println("ReceiverCall监听到来电，并已关闭所有服务！");
					break;
				// 挂断	
				}case TelephonyManager.CALL_STATE_IDLE:{
					System.out.println("ReceiverCall监听到电话已挂断！" + "----i--->" + whitch);
					if(whitch != -1){
						switch(whitch){
							case 0:{
								UtilService.startService((ActivityDoing)context, DataApplication.SERVICE_TYPE_POCKET);
								break;
								
							}case 1:{
								UtilService.startService((ActivityDoing)context, DataApplication.SERVICE_TYPE_HAND);
								break;
								
							}case 2:{
								UtilService.startService((ActivityDoing)context, DataApplication.SERVICE_TYPE_AWAY);
								break;
								
							}
						}
					}
					break;					
				}	
			}		
		}
		
	}	
	
	
}