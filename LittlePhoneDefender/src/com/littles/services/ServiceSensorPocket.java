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
 
public class ServiceSensorPocket extends Service {
	
	private float lightValue;
	private float proxiValue;
	private SensorManager sensorManager;
	private Sensor lightSensor;// ���ߴ�����
	private Sensor proxiSensor;// �����Ӧ��
	private Sensor accelSensor;// ���ٶȸ�Ӧ��
	private MySensorListener mySensorListener;
	
	private long startTime = 0;
	private long currentTime = 0;
	
	private boolean flag = true;

	private float linearAccele[] = new float[3];
	
	private static Activity mThis = null;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
				
		sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		proxiSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mySensorListener = new MySensorListener();

		super.onCreate();
		
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		System.out.println("����SensorService�� --->����ģʽ10�����");	
		startTime = System.currentTimeMillis();
		//ע��--->���ߴ�����
		sensorManager.registerListener(mySensorListener, lightSensor, SensorManager.SENSOR_DELAY_GAME);	
		//ע��--->���봫����
		sensorManager.registerListener(mySensorListener, proxiSensor, SensorManager.SENSOR_DELAY_GAME);	
		//ע��--->���ٶȴ�����
		sensorManager.registerListener(mySensorListener, accelSensor, SensorManager.SENSOR_DELAY_GAME);	
		
		//return super.onStartCommand(intent, flags, startId);
		return START_REDELIVER_INTENT;
		
	}
	
	
	@Override
	public void onDestroy() {
		
		// ע��Sensor�ļ�����
		sensorManager.unregisterListener(mySensorListener);		
		// ֹͣ������
		MediaAlarm.stopMedia(ServiceSensorPocket.this);
		System.out.println("��ע��Sensor������--->����ģʽ�ر�");
		super.onDestroy();
		
	}
	
	class MySensorListener implements SensorEventListener{
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		public void onSensorChanged(SensorEvent event) {
			
			 currentTime = System.currentTimeMillis();
			 
			 long time = currentTime - startTime - DataApplication.SERVICE_BUFFER_TIME;
			 
			 if(time > 0 && time < 20){
										
					UtilToast.showToast(mThis, getResources().getString(R.string.toast_pocket_started), DataApplication.TOAST_SERVICE_STARTED);
					UtilNotification.setNotification(mThis, ActivityDoing.class, 
				    		getResources().getString(R.string.notification_title), 
				    		getResources().getString(R.string.notification_text_pocket));
				    startForeground(0x1982, UtilNotification.getNotification());  
			 }
			 System.out.println("=================================");
			 if(time > 0){				 			 												
				synchronized(sensorManager){
					 
			        switch (event.sensor.getType()){
			        
				        case Sensor.TYPE_LIGHT:{
				        	
				        	lightValue = event.values[0];
							System.out.println("���ߴ�����--->" + lightValue);
							if(flag == true && lightValue > 100.0){

								MediaAlarm.startMedia(ServiceSensorPocket.this);
							}
				        	break;
				        }case Sensor.TYPE_PROXIMITY:{
				        	
				        	proxiValue = event.values[0];
							System.out.println("���봫����--->" + proxiValue);
							if(flag == true && proxiValue == 1.0){

								MediaAlarm.startMedia(ServiceSensorPocket.this);
								
							}
				        	break;
				        }case Sensor.TYPE_ACCELEROMETER:{
				 
				        	linearAccele = UtilSensor.getLinearAccele(event);
				 
							//System.out.println("���ٶȴ�����--->" + linearAccele[0]);
							//System.out.println("���ٶȴ�����--->" + linearAccele[1]);
							//System.out.println("���ٶȴ�����--->" + linearAccele[2]);

							if(flag == true && (linearAccele[0] > 10 || linearAccele[0] < -10 || linearAccele[1] > 10 || linearAccele[1] < -10 || linearAccele[2] > 20 || linearAccele[2] < -20)){				
									
								MediaAlarm.startMedia(ServiceSensorPocket.this);
								flag = false;
								System.out.println("xxxxxxx--->" + linearAccele[0]);
								System.out.println("yyyyyyy--->" + linearAccele[1]);
								System.out.println("zzzzzzz--->" + linearAccele[2]);

							}
								
							if(linearAccele[0] > 20 || linearAccele[0] < -20){
								if(mThis != null){
									System.out.println("���ٶȴ�����--->" + linearAccele[0]);
						
									UtilService.closeService(mThis, DataApplication.SERVICE_TYPE_POCKET);									
									System.out.println("Robber--->ҡһҡ" + "=====flag--->" + flag);		
									
								}							
							}
							
							break;
							
						}
							
													
					}

				       
			}	
		}
			
	}
 }		
	
	public static void setActivityContext(Activity context){
		mThis = context;
	}
		  
	
}