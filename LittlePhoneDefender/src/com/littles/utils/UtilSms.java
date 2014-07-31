package com.littles.utils;

import java.util.ArrayList;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import android.net.Uri;
import android.telephony.SmsManager;

public class UtilSms {
	
	public static void sendSms(String number, String sms, Context context){
		
		if(!UtilString.isEmpty(sms) && !UtilString.isEmpty(number)){
			
			SmsManager smsManager=SmsManager.getDefault(); 

			//---如果短信内容过多，先拆分后发送，不然短信发不出去
			ArrayList<String> msgs = smsManager.divideMessage(sms);
			
			for(String msg : msgs){
				
		        smsManager.sendTextMessage(number, null, msg, null, null); 	        
			
			}

			System.out.println("=======发送成功=======>>>>" + sms);
		}
				       
	}
	/*设置短信为已读*/
	public static void setRead(String thread_id, Context context){
				
		Uri uri = Uri.parse("content://sms/");
		
		ContentValues values = new ContentValues();

		values.put("read", "1");
		
		context.getContentResolver().update(uri, values, "address =? ", new String[]{thread_id});


	}
	
	public static boolean deleteSms(Context context, String number) throws SQLException{

			Uri uri = Uri.parse("content://sms/");			
			
			int rows = context.getContentResolver().delete(uri,  "address=? or address =?", new String[] { number,
				"+86" + number });
			
			
			if(rows > 0){
				
				return true;
				
			}
			
			return false;
			
	}

	public static void sendSMS(String number, String content, Context context){
		
		String SENT_SMS_ACTION="SENT_SMS_ACTION";
		String DELIVERED_SMS_ACTION="DELIVERED_SMS_ACTION";

		//create the sentIntent parameter
		Intent sentIntent=new Intent(SENT_SMS_ACTION);
		PendingIntent sentPI=PendingIntent.getBroadcast(context, 0, sentIntent, 0);

		//create the deilverIntent parameter
		Intent deliverIntent=new Intent(DELIVERED_SMS_ACTION);
		PendingIntent deliverPI=PendingIntent.getBroadcast(context, 0, deliverIntent, 0);
		//register the Broadcast Receivers
		context.registerReceiver(new BroadcastReceiver(){
					@Override
					public void onReceive(Context context,Intent _intent)
					{
												
					}
				},
				new IntentFilter(SENT_SMS_ACTION));
				context.registerReceiver(new BroadcastReceiver(){
					@Override
					public void onReceive(Context context,Intent _intent)
					{
								
					}
				},
				new IntentFilter(DELIVERED_SMS_ACTION));
		
       	SmsManager smsManager=SmsManager.getDefault(); 
       	System.out.println("===================>>>>" + content);
        //发送信息 
        smsManager.sendTextMessage(number, null, content, sentPI, deliverPI); 												
		
	}
	
	
}