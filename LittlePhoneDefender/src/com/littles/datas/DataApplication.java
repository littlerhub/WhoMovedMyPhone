package com.littles.datas;

public class DataApplication{

	//---服务模式
	public static String SERVICE_TYPE_POCKET = "MODE_POCKET";//---口袋模式 
	public static String SERVICE_TYPE_HAND = "MODE_HAND"; //---手持模式
	public static String SERVICE_TYPE_AWAY = "MODE_AWAY";  //---离开模式
	
	//---警报声的状态
	//public final static int MEDIA_STATE_PAUSE = 0; //---已暂停
	public final static int MEDIA_STATE_STOP = 0;    //---已停止
	public final static int MEDIA_STATE_PLAY = 1;    //---播放中	
	
	//---用户开启模式后的缓冲时间
	public static long SERVICE_BUFFER_TIME = 10000;
	
	//---加速度传感器的灵敏度
	public static final int SENSOR_ACCELE_SHAKE = 0; //---摇一摇
	public static final int SENSOR_ACCELE_ROBBER = 1;//防抢
	public static final int SENSOR_ACCELE_SUPER = 2; //无敌
	
	//---Toast类型
	public static final int TOAST_SERVICE_STARTING = 0; //---正在开启Service提示
	public static final int TOAST_SERVICE_STARTED = 1;  //---已开启Service提示
	public static final int TOAST_SERVICE_CLOSEED = 2;  //---已关闭Service提示
	
	public static final int ANIM_ACTIVITY_ENTER = 0;//---Activity进入
	public static final int ANIM_ACTIVITY_EXIT = 1;//---Activity退出
	
	public static final String DONE_PASSWORD_SP = "DonePassword";
	public static final String DONE_PASSWORD_SP_KEY = "password";
	public static final String DONE_NUMBER_SP = "DoneNumber";
	public static final String DONE_NUMBER_SP_KEY = "number";
	public static final String DONE_START_SP = "DoneStart";
	public static final String DONE_START_SP_KEY = "start";
	public static final String DONE_SUBID_SP = "DoneSubID";
	public static final String DONE_SUBID_SP_KEY = "subid";
	
}

