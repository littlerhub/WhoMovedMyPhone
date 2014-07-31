package com.littles.services;

import com.littles.datas.DataDone;
import com.littles.utils.UtilLocation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class ServiceSendLocation extends Service {

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
		
		UtilLocation.sendLocation(number, this);	
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}