package com.littles.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class UtilScreenSize {
	/**
	 * ȡ����Ļ�Ŀ�͸�
	 * @param aContext
	 * @return float[0]:screenWidth;
	 * 		   float[1]:screenHeight;
	 */
	public static float[] getScreenSize(Activity aContext){
		// ȡ����Ļ�Ŀ�͸�
		DisplayMetrics dm=new DisplayMetrics();
		aContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
	    float displayW = dm.widthPixels;
	    float displayH = dm.heightPixels;
	    
	    return new float[]{displayW, displayH};
	}
	
}
