package com.littles.utils;

import android.hardware.SensorEvent;

public class UtilSensor {
	
	private static float gravity[] = new float[3];
	private static float linearAccele[] = new float[3];
	
	//---取得线性加速度
	public static float[] getLinearAccele(SensorEvent event){
		
		float alpha = 0.8f;
		//  过滤之后的重力加速度
		gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
		gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
		gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];	 
		//  计算得到线性加速度
		linearAccele[0] = event.values[0] - gravity[0];
		linearAccele[1] = event.values[1] - gravity[1];
		linearAccele[2] = event.values[2] - gravity[2];	
		
		return linearAccele;
		
	}

}
