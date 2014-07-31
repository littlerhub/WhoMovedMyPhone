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
 * Service�Ĺ�����,�������������رա���ѯ
 * @author LittleBoy
 * 17:34 2013-04-12
 */
public class UtilService {
		
	private static boolean[] servicesState = new boolean[3];
	private static Intent serviceIntent = null;
	private static String toastType;
	
	// ͨ��ActivityManager������Ŀǰ�����ķ����в�ѯ�Ƿ���ָ���ķ���
	public static boolean isServiceExisted(Context context, String serviceClassName) {
    	
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        
        // ���ϵͳ���з���
        if(serviceList.size() > 0) {
        	// �������з���
        	for(int i = 0; i < serviceList.size(); i++) {
                RunningServiceInfo serviceInfo = serviceList.get(i);
                ComponentName serviceName = serviceInfo.service;
                System.out.println("��������--->" + serviceName.getClassName());
                // �����ָ���ķ��񣬷���true
                if(serviceName.getClassName().equals(serviceClassName)) {
                    return true;
                }
            } 
            
        }
        // ���ϵͳ��û���񣬻���ûָ�������򷵻�false
        return false;
        	             
    }
	
	// ͨ���Լ������SharedPreference����ѯ�Ƿ����Լ��ķ���
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
	 * ��ָ��service
	 * @param context
	 */
	public static void startService(Activity mThis, String type){		

		if(type.equals(DataApplication.SERVICE_TYPE_POCKET)){
			
			serviceIntent = new Intent(mThis, ServiceSensorPocket.class);
			ActivityDoing.mStartPocket.setVisibility(View.GONE);
			ActivityDoing.mClosePocket.setVisibility(View.VISIBLE);		
			ServiceSensorPocket.setActivityContext(mThis);
			servicesState[0] = true;
			toastType = "�ڴ�";
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_HAND)){
			
			serviceIntent = new Intent(mThis, ServiceSensorHand.class);	
			ActivityDoing.mStartHand.setVisibility(View.GONE);
			ActivityDoing.mCloseHand.setVisibility(View.VISIBLE);
			ServiceSensorHand.setActivityContext(mThis);
			servicesState[1] = true;
			toastType = "�ֳ�";
			
		}else if(type.equals(DataApplication.SERVICE_TYPE_AWAY)){
			
			serviceIntent = new Intent(mThis, ServiceSensorAway.class);	
			ActivityDoing.mStartAway.setVisibility(View.GONE);
			ActivityDoing.mCloseAway.setVisibility(View.VISIBLE);	
			ServiceSensorAway.setActivityContext(mThis);
			servicesState[2] = true;
			toastType = "Զ��";
			
		}
		UtilToast.showToast(mThis, "10���,������" + toastType + "��ģʽ", DataApplication.TOAST_SERVICE_STARTING);
		start(mThis);
	}	
	
	/**
	 * �ر�����service
	 * @param context
	 */
	public static int closeServices(Context context){	
		int whitch = -1;
		// ����Ҫ�ж��Ƿ��з���
		if(hasService(context) == -1){
			return -1;
		}
		// �����йرհ�ť��ʧ
		ActivityDoing.mClosePocket.setVisibility(View.GONE);
		ActivityDoing.mCloseHand.setVisibility(View.GONE);
		ActivityDoing.mCloseAway.setVisibility(View.GONE);
		
		// ��ȡ��ǰ����Service״̬
		boolean states[] = DataServicesState.readServicesState(context);
		
		// �ر����е�ǰ״̬Ϊtrue��service
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
	 * �ر�ָ��service
	 * @param context
	 */
	public static void closeService(Activity mThis, String type){		
		// ����Ҫ�ж��Ƿ��з���
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
		UtilToast.showToast(mThis, "�ѹرա�" + toastType + "��ģʽ��", DataApplication.TOAST_SERVICE_CLOSEED);
		close(mThis);
		
	}
	
	private static void close(Context context){
		
		context.stopService(serviceIntent);	
		// ���Ҫ�����Service״̬
		DataServicesState.saveServicesState(context, servicesState);

	}
	
	private static void start(Context context){
		context.startService(serviceIntent);
		// �����Service״̬
		DataServicesState.saveServicesState(context, servicesState);								
		
	}
		
}
