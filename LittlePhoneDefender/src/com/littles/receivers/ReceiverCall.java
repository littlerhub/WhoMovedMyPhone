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
 * ֻ������״̬�£��رշ���
 * @author LittleBoy
 * 2013-04-13 20:15
 */
public class ReceiverCall extends BroadcastReceiver {
	
	private static int whitch = -1;
	
	@Override
	public void onReceive(Context context, Intent intent) {

		if(intent.getAction().equals("android.intent.action.PHONE_STATE")){		
			//����һ���������������绰��״̬
			TelephonyManager manager = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
			
			switch(manager.getCallState()){
				// ����
				case TelephonyManager.CALL_STATE_RINGING:{
					// �ر����з���
					whitch = UtilService.closeServices(context);
					System.out.println("ReceiverCall���������磬���ѹر����з���");
					break;
				// �Ҷ�	
				}case TelephonyManager.CALL_STATE_IDLE:{
					System.out.println("ReceiverCall�������绰�ѹҶϣ�" + "----i--->" + whitch);
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