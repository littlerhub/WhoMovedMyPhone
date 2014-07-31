package com.littles.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class UtilScreenLock {
	
	private static WakeLock mWakeLock;
	
	/**
	 * ǿ�Ƶ�����Ļ
	 * @param mThis
	 */
	public static void acquireWakeLock(Context context) {

        if (mWakeLock == null) {
        	
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);  
            mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK //---��Ļ���ֵ���
                            | PowerManager.FULL_WAKE_LOCK  //---��Ļ����
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP  //---ǿ�Ƶ���
                            | PowerManager.ON_AFTER_RELEASE, 
                            context.getClass().getCanonicalName());  
            mWakeLock.acquire();  

        }
  
    }
  
	/**
	 * �ͷ���
	 */
    public static void releaseWakeLock() {
    	
        if (mWakeLock != null && mWakeLock.isHeld()) {
        	mWakeLock.release();
        	mWakeLock = null;
        }
        
    }
	
    /**
     * �ж���Ļ�Ƿ�������״̬
     * @param context
     * @return
     */
    public final static boolean isScreenLocked(Context context) {
        
    	android.app.KeyguardManager mKeyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        
        return mKeyguardManager.inKeyguardRestrictedInputMode();
        
    }
    
}