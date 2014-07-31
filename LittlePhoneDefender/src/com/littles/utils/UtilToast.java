package com.littles.utils;

import com.littles.R;
import com.littles.datas.DataApplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UtilToast {
	/**
	 * ��ʾ���Ƶ�Toast
	 * @param mThis ������
	 * @param text Toast��ʾ������
	 * @param type Toast���ͣ�0�����ڿ���Service  1���ѿ���Service  2���ѹر�Service
	 */
	public static void showToast(Activity mThis, String text, int type){
		if(mThis == null){
			return;
		}
		Toast mToast = new Toast(mThis);
    	mToast.setDuration(Toast.LENGTH_SHORT);
    	
		LayoutInflater inflater = mThis.getLayoutInflater();
		View mView = inflater.inflate(R.layout.toast_all, null);

		LinearLayout mToastBg = (LinearLayout)mView.findViewById(R.id.ll_bg);
		switch(type){
			case DataApplication.TOAST_SERVICE_STARTING:{
				mToastBg.setBackgroundColor(mThis.getResources().getColor(R.color.orange));
				break;
			}case DataApplication.TOAST_SERVICE_STARTED:{
				mToastBg.setBackgroundColor(mThis.getResources().getColor(R.color.green));
				break;
			}case DataApplication.TOAST_SERVICE_CLOSEED:{
				mToastBg.setBackgroundColor(mThis.getResources().getColor(R.color.red));
				break;
			}
		}
		
		TextView mToastText = (TextView)mView.findViewById(R.id.tv_content);				
		mToastText.setText(text);		
		mToast.setView(mView);
		
    	mToast.show();
		
    }	
	
}
