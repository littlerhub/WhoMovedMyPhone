package com.littles.medias;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.littles.*;
import com.littles.datas.DataApplication;
import com.littles.services.ServiceScreenLock;
import com.littles.utils.UtilScreenLock;

public class MediaAlarm {
	// 操作音频：加载、播放、暂停、停止、跳跃
	private static MediaPlayer mMedia;
	// 设置音量
	private static AudioManager mAudio;
	// 记录音频的当前状态
	public static int mediaState = 0;
	// 手机的最大音量值
	private static int maxVolume = 0;
	// 线程开关标志
	private static boolean flag = false;
	
	private static Thread th = null;
	
	private static Service context;
        
	public static void startMedia(Service context){

		// 设置所在的服务
		MediaAlarm.context = context;
		
		if(mediaState != DataApplication.MEDIA_STATE_PLAY){
			
			//---如果处于锁屏状态，开启强制点亮屏幕服务
			if(UtilScreenLock.isScreenLocked(context)){
				Intent screenService = new Intent(context, ServiceScreenLock.class);		
				context.startService(screenService);
			}
			
			init();
			setMediaState(DataApplication.MEDIA_STATE_PLAY);
			
		}	
		
		System.out.println("MediaAlarm--->警报声响起");

	}
	public static void stopMedia(Service context){
		// 设置所在的服务
		MediaAlarm.context = context;
		
		if(mediaState != DataApplication.MEDIA_STATE_STOP){

			setMediaState(DataApplication.MEDIA_STATE_STOP);
			close();			
			//---关闭强制点亮屏幕服务
			Intent screenService = new Intent(context, ServiceScreenLock.class);		
			context.stopService(screenService);
			System.out.println("MediaAlarm--->警报声停止");
		}

	}
        
    private static void close() {
    	// 线程停止
    	flag = false;
    	mMedia = null;
		
	}
	// 初始化操作
	private static void init() {
		
		if(mMedia == null){
			// 加载音频
			mMedia = MediaPlayer.create(context, R.raw.alarm);
			// 设置循环播放
	        mMedia.setLooping(true);
	        
	        mAudio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	        // 取得手机音量的最大值
	        maxVolume = mAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	        // 开启线程
	        flag = true;
	        th = new MyThread();
	        th.start();
		}
       		
	}
	
	// 根据传入的状态，设置对应的音频状态
    private static void setMediaState(int state){
    	
		switch(state){
		
			case DataApplication.MEDIA_STATE_PLAY:{
				/*try {
					mMedia.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				mMedia.start();
				mediaState = DataApplication.MEDIA_STATE_PLAY;
				
				break;				
			}/*case DataApplication.MEDIA_STATE_PAUSE:{
				
				mMedia.pause();					
				mediaState = DataApplication.MEDIA_STATE_PAUSE;
				
				break;				
			}*/case DataApplication.MEDIA_STATE_STOP:{
				if(mMedia != null){
					
					mMedia.pause();
					mMedia.stop();
						
				}					
																		
				mediaState = DataApplication.MEDIA_STATE_STOP;
						
				break;
			}
		
		}
    }
    
    public static int getMediaState(){
    	return mediaState;
    }
    
    // 在子线程中不断设置音量为最大，也就是不让用户减小音量
    static class MyThread extends Thread{

		public void run() {

			while(flag){
				// 设置当前音量值
				mAudio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, AudioManager.FLAG_PLAY_SOUND);
				
				try {
					
					Thread.sleep(50);
					
				} catch (InterruptedException e) {

					e.printStackTrace();
					
				}
			}
			
		}
    	
    } 
}
