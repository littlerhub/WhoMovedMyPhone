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
	private Sensor acceleSensor;//���ٶȴ�����
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
		
		System.out.println("����SensorService�� --->Զ��ģʽ����");	
		startTime = System.currentTimeMillis();
		//ע��--->���ߴ����������ٶȸ�Ӧ��
		sensorManager.registerListener(mySensorListener, acceleSensor, SensorManager.SENSOR_DELAY_GAME);	
		
		//return super.onStartCommand(intent, flags, startId);
		return START_REDELIVER_INTENT;
		
	}
	
	
	@Override
	public void onDestroy() {
		
		//��Service���ٵ�ʱ��ע��Sensor�ļ�����
		sensorManager.unregisterListener(mySensorListener);
		// ֹͣ������
		MediaAlarm.stopMedia(ServiceSensorAway.this);
		System.out.println("��ע��Sensor������--->�޵�ģʽ�ر�");
		super.onDestroy();
		
	}
	
	class MySensorListener implements SensorEventListener{
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}

		public void onSensorChanged(SensorEvent event) {
			
			currentTime = System.currentTimeMillis();
			
			linearAccele = UtilSensor.getLinearAccele(event);
			
			/*System.out.println("���ٶȴ�����--->" + linearAccele[0]);
			System.out.println("���ٶȴ�����--->" + linearAccele[1]);
			System.out.println("���ٶȴ�����--->" + linearAccele[2]);	*/
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
			// ����ʱ��֮����ȡ���ݽ����ж�
			if(time > 0){
				//System.out.println("����ʱ��--->" + (currentTime - startTime));						
						
				if(flag == true && (linearAccele[2] > 1.0 || linearAccele[2] < -1.0 || linearAccele[1] > 1.0 || linearAccele[1] < -1.0 || linearAccele[0] > 1.0 || linearAccele[0] < -1.0)){
					System.out.println("���ٶȴ�����--->" + linearAccele[0]);
					System.out.println("���ٶȴ�����--->" + linearAccele[1]);
					System.out.println("���ٶȴ�����--->" + linearAccele[2]);	
					MediaAlarm.startMedia(ServiceSensorAway.this);
					flag = false;
				}
				
				if(linearAccele[0] > 15 || linearAccele[0] < -15){
					if(mThis != null){

						UtilService.closeService(mThis, DataApplication.SERVICE_TYPE_AWAY);

						System.out.println("Robber--->ҡһҡ");	
					}							
				}
			}
		}

	}
		 
	public static void setActivityContext(Activity context){
		mThis = context;
	}
	
}