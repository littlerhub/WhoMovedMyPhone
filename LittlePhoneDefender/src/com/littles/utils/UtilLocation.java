package com.littles.utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.littles.services.ServiceSendLocation;

import android.content.Context;
import android.content.Intent;

public class UtilLocation {
	public static LocationClient mLocationClient = null;
	public static String mAddress;  
	public static MyLocationListenner myListener;
	public static boolean isOk = false;
	public static String number;
	
	public static void sendLocation(String number, Context context) {
		
		myListener = new MyLocationListenner(context);
		UtilLocation.number = number;
		mLocationClient = new LocationClient(context);
		mLocationClient.registerLocationListener( myListener );
		setLocationOption();
		mLocationClient.start();
		
	}
	
	
	//������ز���
	public static void setLocationOption(){
		LocationClientOption option = new LocationClientOption();
		//---��GPS
		option.setOpenGps(true);	
		//---������������
		option.setCoorType("bd09ll");		
		//---��ʾ�����ַ
		option.setAddrType("all");			
		//---����ɨ��ʱ��
		option.setScanSpan(900);
		//---�������綨λ����
		option.setPriority(LocationClientOption.NetWorkFirst);
		
		option.disableCache(true);		
		mLocationClient.setLocOption(option);
		
	}
	
	
	static class MyLocationListenner implements BDLocationListener {
		
		private Context context;
		
		public MyLocationListenner(Context context){
			this.context = context;
		}
		
		public void onReceiveLocation(BDLocation location) {
			if (location == null){
				return ;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("���ֻ���λ��");
			sb.append("\n��1����γ�� : " + location.getLongitude() + ", " + location.getLatitude());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

				sb.append("\n��2��λ�� : " + location.getAddrStr());

			}

			
			if(!UtilString.isEmpty(sb.toString())){

				UtilSms.sendSms(number, sb.toString(), context);	
				
				//---���ͳɹ�֮�󣬽����Ͷ��ŵĺ�̨Service�ر�
				Intent serviceIntent = new Intent(context, ServiceSendLocation.class);
				context.stopService(serviceIntent);
			}
			
		}

		public void onReceivePoi(BDLocation arg0) {
		
			
		}
		
	}
	
}
