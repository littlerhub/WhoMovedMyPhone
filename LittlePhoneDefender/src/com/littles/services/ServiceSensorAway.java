package com.littles.services;

import com.littles.R;
import com.littles.activitys.ActivityDoing;
import com.littles.datas.DataApplication;
import com.littles.medias.MediaAlarm;
import com.littles.utils.UtilNotification;
import com.littles.utils.UtilSensor;
import com.littles.utils.UtilService;
import com.littles.utils.UtilToast;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

public class ServiceSensorAway extends Service {

	private float linearAccele[] = new float[3];
	
	private boolean flag = true;
	
	private SensorManager sensorManager;
	private Sensor acceleSensor;//加速度传感器
	private MySensorListener mySensorListener;
	
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
		
		System.out.println("启动SensorService类 --->远离模式开启");	
		startTime = System.currentTimeMillis();
		//注册--->光线传感器，加速度感应器
		sensorManager.registerListener(mySensorListener, acceleSensor, SensorManager.SENSOR_DELAY_GAME);	
		
		//return super.onStartCommand(intent, flags, startId);
		return START_REDELIVER_INTENT;
		
	}
	
	
	@Override
	public void onDestroy() {
		
		//当Service销毁的时候，注销Sensor的监听器
		sensorManager.unregisterListener(mySensorListener);
		// 停止警报声
		MediaAlarm.stopMedia(ServiceSensorAway.this);
		System.out.println("已注销Sensor监听器--->无敌模式关闭");
		super.onDestroy();
		
	}
	
	class MySensorListener implements SensorEventListener{
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		public void onSensorChanged(SensorEvent event) {
			
			currentTime = System.currentTimeMillis();
			
			linearAccele = UtilSensor.getLinearAccele(event);
			
			/*System.out.println("加速度传感器--->" + linearAccele[0]);
			System.out.println("加速度传感器--->" + linearAccele[1]);
			System.out.println("加速度传感器--->" + linearAccele[2]);	*/
			long time = currentTime - startTime - DataApplication.SERVICE_BUFFER_TIME;
			//System.out.println("===============================  " + time);
			if(time > 0 && time < 20){
				
				if(mThis != null){
					
					UtilToast.showToast(mThis, getResources().getString(R.string.toast_away_started), DataApplication.TOAST_SERVICE_STARTED);
					UtilNotification.setMyNotification(mThis, ActivityDoing.class, 
				    		getResources().getString(R.string.notification_title), 
				    		getResources().getString(R.string.notification_text_leave));
				    startForeground(0x1982, UtilNotification.getNotification());  
				}				
				
			}
			// 缓冲时间之后，再取数据进行判断
			if(time > 0){
				//System.out.println("经过时间--->" + (currentTime - startTime));						
						
				if(flag == true && (linearAccele[2] > 1.0 || linearAccele[2] < -1.0 || linearAccele[1] > 1.0 || linearAccele[1] < -1.0 || linearAccele[0] > 1.0 || linearAccele[0] < -1.0)){
					System.out.println("加速度传感器--->" + linearAccele[0]);
					System.out.println("加速度传感器--->" + linearAccele[1]);
					System.out.println("加速度传感器--->" + linearAccele[2]);	
					MediaAlarm.startMedia(ServiceSensorAway.this);
					flag = false;
				}
				
				if(linearAccele[0] > 15 || linearAccele[0] < -15){
					if(mThis != null){

						UtilService.closeService(mThis, DataApplication.SERVICE_TYPE_AWAY);

						System.out.println("Robber--->摇一摇");	
					}							
				}
			}
		}

	}
		 
	public static void setActivityContext(Activity context){
		mThis = context;
	}
	
}