package com.littles.utils;

import com.littles.datas.DataDone;

import android.content.Context;

public class UtilBootCheck {

	/**
	 * ȡ���û��ֻ���ǰ����������״̬
	 * @param context
	 * @return boolean
	 */
	public static boolean isIMSIDifferent(Context context){
		
		boolean flag = false;
		
		String oldIMSI = DataDone.readDoneSubID(context);		
		String newIMSI = UtilIMSI.getSubscriberID(context);
		
	    if(!UtilString.isEmpty(oldIMSI) && !UtilString.isEmpty(newIMSI)
	    		&& !(oldIMSI.trim()).equals((newIMSI.trim()))){
	    	
	    	flag = true;
	    	
	    }
	    
	    return flag;		
		
	}
	
}
