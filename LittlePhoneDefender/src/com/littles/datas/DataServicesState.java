package com.littles.datas;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 保存/读取 各Service的状态数据
 * @author LittleBoy
 * 2013-04-12 18:07
 */
public class DataServicesState {
	
	// 保存各Service的状态数据
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
	
	// 读取各Service的状态数据
	public static boolean[] readServicesState(Context context){
		
		SharedPreferences sp = context.getSharedPreferences("ServicesState", Context.MODE_PRIVATE);
		boolean[] servicesState = new boolean[3];
		
		servicesState[0] = sp.getBoolean(DataApplication.SERVICE_TYPE_POCKET, false);// 如果找不到，则返回默认值false
		servicesState[1] = sp.getBoolean(DataApplication.SERVICE_TYPE_HAND, false);
		servicesState[2] = sp.getBoolean(DataApplication.SERVICE_TYPE_AWAY, false);
		
		return servicesState;
		
	}
	
}
