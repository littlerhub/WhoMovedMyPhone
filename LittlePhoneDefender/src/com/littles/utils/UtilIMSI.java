package com.littles.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class UtilIMSI {

	/**
	 * ȡ���û���SIM����Ψһ��ʶ��SubscriberID
	 * @param context
	 * @return ���ܷ���null
	 */
	public static String getSubscriberID(Context context){
		
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		String subscriberId = tm.getSubscriberId();
		
		return subscriberId;
		
	}
	
}
