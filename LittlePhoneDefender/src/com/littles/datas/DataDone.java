package com.littles.datas;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 在内存中存储【盗后追踪】设置的开启密码、安全号码
 * @author LittleBoy
 * 2013-05-17 22:47
 *
 */
public class DataDone {
	
	/**
	 * 保存开启密码
	 */
	public static void saveDonePassword(Context context, String password){
		if(password == null || "".equals(password)){
			return;
		}
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_PASSWORD_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putString(DataApplication.DONE_PASSWORD_SP_KEY, password);
		
		spe.commit();
		
	}	
	//---可能返回""
	public static String readDonePassword(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_PASSWORD_SP, Context.MODE_PRIVATE);
		
		String password = sp.getString(DataApplication.DONE_PASSWORD_SP_KEY, "");
		
		return password;
		
	}
	
	
	
	/**
	 * 保存安全号码
	 */
	public static void saveDoneNumber(Context context, String number){
		if(number == null || "".equals(number)){
			return;
		}
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_NUMBER_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putString(DataApplication.DONE_NUMBER_SP_KEY, number);
		
		spe.commit();
		
	}
	
	public static String readDoneNumber(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_NUMBER_SP, Context.MODE_PRIVATE);
		
		String number = sp.getString(DataApplication.DONE_NUMBER_SP_KEY, "");
		
		return number;
		
	}
	
	
	/**
	 * 一键开启
	 */
	public static void saveDoneStart(Context context, boolean start){

		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_START_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putBoolean(DataApplication.DONE_START_SP_KEY, start);
		
		spe.commit();
		
	}	
	//---可能返回""
	public static boolean readDoneStart(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_START_SP, Context.MODE_PRIVATE);
		
		boolean start = sp.getBoolean(DataApplication.DONE_START_SP_KEY, false);
		
		return start;
		
	}
	
	/**
	 * 保存用户SIM卡的唯一标识
	 * @param context
	 * @param start
	 */
	public static void saveDoneSubID(Context context, String subID){

		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_SUBID_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putString(DataApplication.DONE_SUBID_SP_KEY, subID);
		
		spe.commit();
		
	}	
	//---可能返回""
	public static String readDoneSubID(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_SUBID_SP, Context.MODE_PRIVATE);
		
		String subID = sp.getString(DataApplication.DONE_SUBID_SP_KEY, "");
		
		return subID;
		
	}
	
	
}
