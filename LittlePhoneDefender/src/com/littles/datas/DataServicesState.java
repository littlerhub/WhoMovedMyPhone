package com.littles.datas;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * ����/��ȡ ��Service��״̬����
 * @author LittleBoy
 * 2013-04-12 18:07
 */
public class DataServicesState {
	
	// �����Service��״̬����
	public static void saveServicesState(Context context, boolean[] servicesState){
		if(servicesState == null){
			servicesState = new boolean[]{false, false, false};
		}
		SharedPreferences sp = context.getSharedPreferences("ServicesState", Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putBoolean(DataApplication.SERVICE_TYPE_POCKET, servicesState[0]);
		spe.putBoolean(DataApplication.SERVICE_TYPE_HAND, servicesState[1]);
		spe.putBoolean(DataApplication.SERVICE_TYPE_AWAY, servicesState[2]);
		
		spe.commit();
		
	}
	
	// ��ȡ��Service��״̬����
	public static boolean[] readServicesState(Context context){
		
		SharedPreferences sp = context.getSharedPreferences("ServicesState", Context.MODE_PRIVATE);
		boolean[] servicesState = new boolean[3];
		
		servicesState[0] = sp.getBoolean(DataApplication.SERVICE_TYPE_POCKET, false);// ����Ҳ������򷵻�Ĭ��ֵfalse
		servicesState[1] = sp.getBoolean(DataApplication.SERVICE_TYPE_HAND, false);
		servicesState[2] = sp.getBoolean(DataApplication.SERVICE_TYPE_AWAY, false);
		
		return servicesState;
		
	}
	
}
