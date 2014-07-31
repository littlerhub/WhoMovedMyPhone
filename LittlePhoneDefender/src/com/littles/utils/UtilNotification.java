package com.littles.utils;

import com.littles.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class UtilNotification {

	public static NotificationManager nm = null; 
	public static Notification n = null;
	
	public static void setNotification(Context context, Class<?> bClass, String title, String text){
		if(context == null){
			return;
		}
		n = new Notification(R.drawable.ic_launcher, context.getResources().getString(R.string.app_name), System.currentTimeMillis());
	    PendingIntent pIntent = PendingIntent.getActivity(context, 0, new Intent(context, bClass), 0);
	    n.setLatestEventInfo(context, title, text,  pIntent);	   
	    n.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE; //---“Ù–ß

	}
	
	public static Notification getNotification(){
		
		return n;
		
	}
	
	public static void setMyNotification(Context context, Class<?> bClass, String title, String text){
		if(context == null){
			return;
		}
		n = new Notification(R.drawable.ic_launcher, context.getResources().getString(R.string.app_name), System.currentTimeMillis());
	    PendingIntent pIntent = PendingIntent.getActivity(context, 0, new Intent(context, bClass), 0);
	    n.setLatestEventInfo(context, title, text,  pIntent);	   
	    n.defaults = Notification.DEFAULT_SOUND; //---“Ù–ß

	}
	
}
