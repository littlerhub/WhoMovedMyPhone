package com.littles.activitys;

import com.littles.R;
import com.littles.datas.DataApplication;
import com.littles.datas.DataServicesState;
import com.littles.listeners.ListenerOnActivityBackClick;
import com.littles.listeners.ListenerOnServiceCloseClick;
import com.littles.listeners.ListenerOnImgClick;
import com.littles.listeners.ListenerOnServiceStartClick;
import com.littles.utils.UtilService;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;

public class ActivityDoing extends Activity {

	private Button mBtnBack = null;
	
	public static ImageView mStartPocket;
	public static ImageView mStartHand;
	public static ImageView mStartAway;
	
	public static ImageView mClosePocket;
	public static ImageView mCloseHand;
	public static ImageView mCloseAway;
	
	private ImageView mImgPocket;
	private ImageView mImgHand;
	private ImageView mImgAway;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing);
        
		init();		
		addListeners();
		
    }

    @Override
	protected void onStart() {
    		
		super.onStart();
		
		boolean[] servicesState = DataServicesState.readServicesState(this);
		
		for(int i = 0; i < servicesState.length; i++){
			System.out.println("ActivityMain中的onStart()--->" + i + "--->" + servicesState[i]);
			switch(i){
			
				case 0:{
					if(servicesState[i]){
						mClosePocket.setVisibility(View.VISIBLE);
					}else{
						mStartPocket.setVisibility(View.VISIBLE);
					}
					break;
				}case 1:{
					if(servicesState[i]){
						mCloseHand.setVisibility(View.VISIBLE);
					}else{
						mStartHand.setVisibility(View.VISIBLE);
					}
					break;
				}case 2:{
					if(servicesState[i]){
						mCloseAway.setVisibility(View.VISIBLE);
					}else{
						mStartAway.setVisibility(View.VISIBLE);
					}
					break;
				}
				
			}
			
		}
			
	}

	private void addListeners() {
		
		mBtnBack.setOnClickListener(new ListenerOnActivityBackClick(ActivityDoing.this));
		
		mStartPocket.setOnClickListener(new ListenerOnServiceStartClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_POCKET));
		mStartHand.setOnClickListener(new ListenerOnServiceStartClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_HAND));
		mStartAway.setOnClickListener(new ListenerOnServiceStartClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_AWAY));
		
		mClosePocket.setOnClickListener(new ListenerOnServiceCloseClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_POCKET));
		mCloseHand.setOnClickListener(new ListenerOnServiceCloseClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_HAND));
		mCloseAway.setOnClickListener(new ListenerOnServiceCloseClick(ActivityDoing.this, DataApplication.SERVICE_TYPE_AWAY));
	
		mImgPocket.setOnClickListener(new ListenerOnImgClick(ActivityDoing.this));
		mImgHand.setOnClickListener(new ListenerOnImgClick(ActivityDoing.this));
		mImgAway.setOnClickListener(new ListenerOnImgClick(ActivityDoing.this));
		
	}

	private void init() {
		
		mBtnBack = (Button)findViewById(R.id.mTitleBtnBack);
		
		mStartPocket = (ImageView)findViewById(R.id.iv_startGuardPocket);
		mStartHand = (ImageView)findViewById(R.id.iv_startGuardHand);
		mStartAway = (ImageView)findViewById(R.id.iv_startGuardAway);
		
		mClosePocket = (ImageView)findViewById(R.id.iv_closeGuardPocket);
		mCloseHand = (ImageView)findViewById(R.id.iv_closeGuardHand);
		mCloseAway = (ImageView)findViewById(R.id.iv_closeGuardAway);		
		
		mImgPocket = (ImageView)findViewById(R.id.iv_pocket);
		mImgPocket.setBackgroundResource(R.drawable.bg_guarder_pocket);
		mImgHand = (ImageView)findViewById(R.id.iv_hand);
		mImgHand.setBackgroundResource(R.drawable.bg_guarder_hand);
		mImgAway = (ImageView)findViewById(R.id.iv_away);
		mImgAway.setBackgroundResource(R.drawable.bg_guarder_away);
		
		//---通过判断系统中已有的Service，来初始化自己的Service状态
		switch(UtilService.hasService(this)){
		
			case 0:{
				if(!UtilService.isServiceExisted(this, "com.littles.services.ServiceSensorPocket")){
					UtilService.closeService(this, DataApplication.SERVICE_TYPE_POCKET);
					DataServicesState.saveServicesState(this, null);
				}
				break;
			}case 1:{
				if(!UtilService.isServiceExisted(this, "com.littles.services.ServiceSensorHand")){
					UtilService.closeService(this, DataApplication.SERVICE_TYPE_HAND);
					DataServicesState.saveServicesState(this, null);
				}
				break;
			}case 2:{
				if(!UtilService.isServiceExisted(this, "com.littles.services.ServiceSensorAway")){
					UtilService.closeService(this, DataApplication.SERVICE_TYPE_AWAY);
					DataServicesState.saveServicesState(this, null);
				}
				break;
			}
			
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//---当点击【返回】键时
		if(keyCode == KeyEvent.KEYCODE_BACK){
			
			ListenerOnActivityBackClick.onMyClick(ActivityDoing.this);
			
		}				
		
		return false;
	}
		
}
