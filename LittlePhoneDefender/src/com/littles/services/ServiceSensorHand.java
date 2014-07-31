package com.littles.services;

import com.littles.activitys.ActivityDoing;
import com.littles.datas.DataApplication;
import com.littles.medias.MediaAlarm;
import com.littles.utils.UtilNotification;
import com.littles.utils.UtilSensor;
import com.littles.utils.UtilService;
import com.littles.utils.UtilToast;

import com.littles.*;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
 
public class ServiceSensorHand extends Service {
	
	private SensorManager sensorManager;
	private Sensor acceleSensor;//加速度传感器
	private MySensorListener mySensorListener;
	
	private boolean flag = true;

	private float linearAccele[] = new float[3];
	
	private static Activity mThis = null;
	
	private long startTime = 0;
	private long currentTime = 0;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
				
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);		
		acceleSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mySensorListener = new MySensorListener();
		
		super.onCreate();
		
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startTime = System.currentTimeMillis();
		System.out.println("启动SensorService类 --->防抢模式10秒后开启");	
		//注册--->加速度传感器
		sensorManager.registerListener(mySensorListener, acceleSensor, SensorManager.SENSOR_DELAY_GAME);	
		
		//return super.onStartCommand(intent, flags, startId);		
		return START_REDELIVER_INTENT;
		
	}
		
	
	@Override
	public void onDestroy() {

		sensorManager.unregisterListener(mySensorListener);
		// 停止警报声
		MediaAlarm.stopMedia(ServiceSensorHand.this);
		System.out.println("已注销Sensor监听器--->关闭防抢模式");
		super.onDestroy();
		
	}
	
	class MySensorListener implements SensorEventListener{
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		public void onSensorChanged(SensorEvent event) {
			currentTime = System.currentTimeMillis();
			
			linearAccele = UtilSensor.getLinearAccele(event);
			
			System.out.println("加速度传感器--->" + linearAccele[0]);
			System.out.println("加速度传感器--->" + linearAccele[1]);
			System.out.println("加速度传感器--->" + linearAccele[2]);
			long time = currentTime - startTime - DataApplication.SERVICE_BUFFER_TIME;
			if(time > 0 && time < 20){
				UtilToast.showToast(mThis, getResources().getString(R.string.toast_hand_started), DataApplication.TOAST_SERVICE_STARTED);
			    UtilNotification.setNotification(mThis, ActivityDoing.class, 
			    		getResources().getString(R.string.notification_title), 
			    		getResources().getString(R.string.notification_text_hand));
			    startForeground(0x1982, UtilNotification.getNotification());  
			}
			// 在缓冲时间之后，再取数据进行判断
			if(time > 0){
				
				if(flag == true && (linearAccele[0] > 10 || linearAccele[0] < -10 || linearAccele[1] > 10 || linearAccele[1] < -10 || linearAccele[2] > 20 || linearAccele[2] < -20)){				
					
					MediaAlarm.startMedia(ServiceSensorHand.this);					
					startTime = System.currentTimeMillis();
					flag = false;
					System.out.println("xxxxxxx--->" + linearAccele[0]);
					System.out.println("yyyyyyy--->" + linearAccele[1]);
					System.out.println("zzzzzzz--->" + linearAccele[2]);

				}

				if(flag == false && (currentTime - startTime) > 3000 && (linearAccele[0] > 15 || linearAccele[0] > -15)){
					if(mThis != null){
					
						UtilService.closeService(mThis, DataApplication.SERVICE_TYPE_HAND);
						System.out.println("Robber--->摇一摇" + "=====flag--->" + flag);			
						
					}							
				}
				
			}
			
									
		}

	}
	
	public static void setActivityContext(Activity context){
		mThis = context;
	}
	
	
}