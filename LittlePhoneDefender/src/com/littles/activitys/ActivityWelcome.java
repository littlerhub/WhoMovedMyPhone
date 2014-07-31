package com.littles.activitys;

import com.littles.R;
import com.littles.anims.AnimManager;
import com.littles.datas.DataApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
/**
 * ª∂”≠ΩÁ√Ê
 * @author LittleBoy
 * 16:35 2013-04-12
 */
public class ActivityWelcome extends Activity implements Runnable{
	
	//—”≥Ÿ2.5√Î 
	private final long DISPLAY_DURATION = 2500; 
	
	private Handler mHandler = null; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
                Intent intent = new Intent(ActivityWelcome.this, ActivityMain.class);   
                ActivityWelcome.this.startActivity(intent);  
                AnimManager.getActivityAnim(ActivityWelcome.this, DataApplication.ANIM_ACTIVITY_ENTER);
                ActivityWelcome.this.finish();  
                
			}        	      	
        	
        };
        
        Thread th = new Thread(this);
        th.start();

    }

	public void run() {
		
		mHandler.sendEmptyMessageDelayed(0, DISPLAY_DURATION);
		
	}
}
