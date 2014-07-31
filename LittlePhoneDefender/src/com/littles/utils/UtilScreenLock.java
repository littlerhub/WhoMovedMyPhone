package com.littles.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class UtilScreenLock {
	
	private static WakeLock mWakeLock;
	
	/**
	 * 强制点亮屏幕
	 * @param mThis
	 */
	public static void acquireWakeLock(Context context) {

        if (mWakeLock == null) {
        	
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);  
            mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK //---屏幕保持点亮
                            | PowerManager.FULL_WAKE_LOCK  //---屏幕高亮
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP  //---强制点亮
                            | PowerManager.ON_AFTER_RELEASE, 
                            context.getClass().getCanonicalName());  
            mWakeLock.acquire();  

        }
  
    }
  
	/**
	 * 释放锁
	 */
    public static void releaseWakeLock() {
    	
        if (mWakeLock != null && mWakeLock.isHeld()) {
        	mWakeLock.release();
        	mWakeLock = null;
        }
        
    }
	
    /**
     * 判断屏幕是否处于锁屏状态
     * @param context
     * @return
     */
    public final static boolean isScreenLocked(Context context) {
        
    	android.app.KeyguardManager mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        
        return mKeyguardManager.inKeyguardRestrictedInputMode();
        
    }
    
}