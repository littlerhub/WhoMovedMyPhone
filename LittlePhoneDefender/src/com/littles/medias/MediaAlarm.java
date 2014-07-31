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
	// ������Ƶ�����ء����š���ͣ��ֹͣ����Ծ
	private static MediaPlayer mMedia;
	// ��������
	private static AudioManager mAudio;
	// ��¼��Ƶ�ĵ�ǰ״̬
	public static int mediaState = 0;
	// �ֻ����������ֵ
	private static int maxVolume = 0;
	// �߳̿��ر�־
	private static boolean flag = false;
	
	private static Thread th = null;
	
	private static Service context;
        
	public static void startMedia(Service context){

		// �������ڵķ���
		MediaAlarm.context = context;
		
		if(mediaState != DataApplication.MEDIA_STATE_PLAY){
			
			//---�����������״̬������ǿ�Ƶ�����Ļ����
			if(UtilScreenLock.isScreenLocked(context)){
				Intent screenService = new Intent(context, ServiceScreenLock.class);		
				context.startService(screenService);
			}
			
			init();
			setMediaState(DataApplication.MEDIA_STATE_PLAY);
			
		}	
		
		System.out.println("MediaAlarm--->����������");

	}
	public static void stopMedia(Service context){
		// �������ڵķ���
		MediaAlarm.context = context;
		
		if(mediaState != DataApplication.MEDIA_STATE_STOP){

			setMediaState(DataApplication.MEDIA_STATE_STOP);
			close();			
			//---�ر�ǿ�Ƶ�����Ļ����
			Intent screenService = new Intent(context, ServiceScreenLock.class);		
			context.stopService(screenService);
			System.out.println("MediaAlarm--->������ֹͣ");
		}

	}
        
    private static void close() {
    	// �߳�ֹͣ
    	flag = false;
    	mMedia = null;
		
	}
	// ��ʼ������
	private static void init() {
		
		if(mMedia == null){
			// ������Ƶ
			mMedia = MediaPlayer.create(context, R.raw.alarm);
			// ����ѭ������
	        mMedia.setLooping(true);
	        
	        mAudio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
	        // ȡ���ֻ����������ֵ
	        maxVolume = mAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	        // �����߳�
	        flag = true;
	        th = new MyThread();
	        th.start();
		}
       		
	}
	
	// ���ݴ����״̬�����ö�Ӧ����Ƶ״̬
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
    
    // �����߳��в�����������Ϊ���Ҳ���ǲ����û���С����
    static class MyThread extends Thread{

		public void run() {

			while(flag){
				// ���õ�ǰ����ֵ
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
