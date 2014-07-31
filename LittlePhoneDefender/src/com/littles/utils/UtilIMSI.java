package com.littles.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class UtilIMSI {

	/**
	 * 取得用户的SIM卡的唯一标识：SubscriberID
	 * @param context
	 * @return 可能返回null
	 */
	public static String getSubscriberID(Context context){
		
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		String subscriberId = tm.getSubscriberId();
		
		return subscriberId;
		
	}
	
}
