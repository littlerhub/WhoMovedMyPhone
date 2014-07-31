package com.littles.services;

import com.littles.utils.UtilScreenLock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/**
 * 当报警声响起时，启动此Service，强制点亮屏幕
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
		System.out.println("=============开启强制点亮屏幕============");
		//---强制点亮屏幕
		UtilScreenLock.acquireWakeLock(this);
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		System.out.println("=============关闭强制点亮屏幕============");
		//---释放锁
		UtilScreenLock.releaseWakeLock();
		super.onDestroy();
	}
	
    
}