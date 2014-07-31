package com.littles.utils;

import java.util.List;

import com.littles.activitys.ActivityDoing;
import com.littles.datas.DataApplication;
import com.littles.datas.DataServicesState;
import com.littles.services.ServiceSensorHand;
import com.littles.services.ServiceSensorPocket;
import com.littles.services.ServiceSensorAway;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
/**
 * Service的工具类,包括：开启、关闭、查询
 * @author LittleBoy
 * 17:34 2013-04-12
 */
public class UtilService {
		
	private static boolean[] servicesState = new boolean[3];
	private static Intent serviceIntent = null;
	private static String toastType;
	
	// 通过ActivityManager在所有目前开启的服务中查询是否有指定的服务
	public static boolean isServiceExisted(Context context, String serviceClassName) {
    	
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        
        // 如果系统中有服务
        if(serviceList.size() > 0) {
        	// 遍历所有服务
        	for(int i = 0; i < serviceList.size(); i++) {
                RunningServiceInfo serviceInfo = serviceList.get(i);
                ComponentName serviceName = serviceInfo.service;
                System.out.println("服务名称--->" + serviceName.getClassName());
                // 如果有指定的服务，返回true
                if(serviceName.getClassName().equals(serviceClassName)) {
                    return true;
                }
            } 
            
        }
        // 如果系统中没服务，或者没指定服务，则返回false
        return false;
        	             
    }
	
	// 通过自己保存的SharedPreference来查询是否有自己的服务
	public static int hasService(Context context) {
    	int flag = -1;
		boolean[] states = DataServicesState.readServicesState(context);
		for(int i = 0; i < states.length; i++){
			if(states[i]){
				flag = i;
			}
		}
       
        return flag;
        	             
    }
	
	/**
	 * 打开指定service
	 * @param context
	 */
	public static void startService(Activity mThis, String type){		

		if(type.equals(DataApplication.SERVICE_TYPE_POCKET)){
			
			serviceIntent = new Intent(mThis, ServiceSensorPocket.class);
			ActivityDoing.mStartPocket.setVisibility(View.GONE);
			ActivityDoing.mClosePocket.setVisibility(View.VISIBLE);		
			ServiceSensorPocket.setActivityContext(mThis);
			servicesState[0] = true;
			toastType = "口袋";
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_HAND)){
			
			serviceIntent = new Intent(mThis, ServiceSensorHand.class);	
			ActivityDoing.mStartHand.setVisibility(View.GONE);
			ActivityDoing.mCloseHand.setVisibility(View.VISIBLE);
			ServiceSensorHand.setActivityContext(mThis);
			servicesState[1] = true;
			toastType = "手持";
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_AWAY)){
			
			serviceIntent = new Intent(mThis, ServiceSensorAway.class);	
			ActivityDoing.mStartAway.setVisibility(View.GONE);
			ActivityDoing.mCloseAway.setVisibility(View.VISIBLE);	
			ServiceSensorAway.setActivityContext(mThis);
			servicesState[2] = true;
			toastType = "远离";
			
		}
		UtilToast.showToast(mThis, "10秒后,开启【" + toastType + "】模式", DataApplication.TOAST_SERVICE_STARTING);
		start(mThis);
	}	
	
	/**
	 * 关闭所有service
	 * @param context
	 */
	public static int closeServices(Context context){	
		int whitch = -1;
		// 首先要判断是否有服务
		if(hasService(context) == -1){
			return -1;
		}
		// 将所有关闭按钮消失
		ActivityDoing.mClosePocket.setVisibility(View.GONE);
		ActivityDoing.mCloseHand.setVisibility(View.GONE);
		ActivityDoing.mCloseAway.setVisibility(View.GONE);
		
		// 读取当前所有Service状态
		boolean states[] = DataServicesState.readServicesState(context);
		
		// 关闭所有当前状态为true的service
		for(int i = 0; i < states.length; i++){
			if(states[i]){
				
				switch(i){
					case 0:{
						serviceIntent = new Intent(context, ServiceSensorPocket.class);
						ActivityDoing.mStartPocket.setVisibility(View.VISIBLE);
						whitch = 0;
						break;
						
					}case 1:{						
							serviceIntent = new Intent(context, ServiceSensorHand.class);	
							ActivityDoing.mStartHand.setVisibility(View.VISIBLE);	
							whitch = 1;
						break;
						
					}case 2:{						
							serviceIntent = new Intent(context, ServiceSensorAway.class);
							ActivityDoing.mStartAway.setVisibility(View.VISIBLE);	
							whitch = 2;
						break;
						
					}
				}				
			}							
		}
		
		close(context);
		
		return whitch;
		
	}
	
	/**
	 * 关闭指定service
	 * @param context
	 */
	public static void closeService(Activity mThis, String type){		
		// 首先要判断是否有服务
		if(hasService(mThis) == -1){
			return;
		}
		if(type.equals(DataApplication.SERVICE_TYPE_POCKET)){
			
			serviceIntent = new Intent(mThis, ServiceSensorPocket.class);	
			ActivityDoing.mClosePocket.setVisibility(View.GONE);
			ActivityDoing.mStartPocket.setVisibility(View.VISIBLE);
			servicesState[0] = false;
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_HAND)){
			
			serviceIntent = new Intent(mThis, ServiceSensorHand.class);
			ActivityDoing.mCloseHand.setVisibility(View.GONE);
			ActivityDoing.mStartHand.setVisibility(View.VISIBLE);
		    servicesState[1] = false;
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_AWAY)){
			
			serviceIntent = new Intent(mThis, ServiceSensorAway.class);	
			ActivityDoing.mCloseAway.setVisibility(View.GONE);
			ActivityDoing.mStartAway.setVisibility(View.VISIBLE);
			servicesState[2] = false;
			
		}
		UtilToast.showToast(mThis, "已关闭【" + toastType + "】模式！", DataApplication.TOAST_SERVICE_CLOSEED);
		close(mThis);
		
	}
	
	private static void close(Context context){
		
		context.stopService(serviceIntent);	
		// 最后，要保存各Service状态
		DataServicesState.saveServicesState(context, servicesState);

	}
	
	private static void start(Context context){
		context.startService(serviceIntent);
		// 保存各Service状态
		DataServicesState.saveServicesState(context, servicesState);								
		
	}
		
}
