package com.littles.services;

import com.littles.R;
import com.littles.datas.DataDone;
import com.littles.utils.UtilIMSI;
import com.littles.utils.UtilSms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class ServiceSendIMSI extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		String number = DataDone.readDoneNumber(this);
		String IMSI = UtilIMSI.getSubscriberID(this);
		UtilSms.sendSms(number, this.getResources().getString(R.string.text_done_sms1) + IMSI + 
				"\n" + this.getResources().getString(R.string.text_done_sms2), this);

		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}