package com.littles.datas;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * ���ڴ��д洢������׷�١����õĿ������롢��ȫ����
 * @author LittleBoy
 * 2013-05-17 22:47
 *
 */
public class DataDone {
	
	/**
	 * ���濪������
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
	//---���ܷ���""
	public static String readDonePassword(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_PASSWORD_SP, Context.MODE_PRIVATE);
		
		String password = sp.getString(DataApplication.DONE_PASSWORD_SP_KEY, "");
		
		return password;
		
	}
	
	
	
	/**
	 * ���氲ȫ����
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
	 * һ������
	 */
	public static void saveDoneStart(Context context, boolean start){

		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_START_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putBoolean(DataApplication.DONE_START_SP_KEY, start);
		
		spe.commit();
		
	}	
	//---���ܷ���""
	public static boolean readDoneStart(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_START_SP, Context.MODE_PRIVATE);
		
		boolean start = sp.getBoolean(DataApplication.DONE_START_SP_KEY, false);
		
		return start;
		
	}
	
	/**
	 * �����û�SIM����Ψһ��ʶ
	 * @param context
	 * @param start
	 */
	public static void saveDoneSubID(Context context, String subID){

		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_SUBID_SP, Context.MODE_PRIVATE);
		SharedPreferences.Editor spe = sp.edit();
		
		spe.putString(DataApplication.DONE_SUBID_SP_KEY, subID);
		
		spe.commit();
		
	}	
	//---���ܷ���""
	public static String readDoneSubID(Context context){
		
		SharedPreferences sp = context.getSharedPreferences(DataApplication.DONE_SUBID_SP, Context.MODE_PRIVATE);
		
		String subID = sp.getString(DataApplication.DONE_SUBID_SP_KEY, "");
		
		return subID;
		
	}
	
	
}
