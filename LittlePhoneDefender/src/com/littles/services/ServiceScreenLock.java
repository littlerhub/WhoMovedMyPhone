package com.littles.services;

import com.littles.utils.UtilScreenLock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * ������������ʱ��������Service��ǿ�Ƶ�����Ļ
 * @author LittleBoy
 *
 */
public class ServiceScreenLock extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}

	@Override
	public void onCreate() {
		
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("=============����ǿ�Ƶ�����Ļ============");
		//---ǿ�Ƶ�����Ļ
		UtilScreenLock.acquireWakeLock(this);
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		System.out.println("=============�ر�ǿ�Ƶ�����Ļ============");
		//---�ͷ���
		UtilScreenLock.releaseWakeLock();
		super.onDestroy();
	}
	
    
}