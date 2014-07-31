package com.littles.activitys;

import com.littles.R;
import com.littles.datas.DataApplication;
import com.littles.datas.DataDone;
import com.littles.dialogs.DialogCheckPassword;
import com.littles.dialogs.DialogDoneNumber;
import com.littles.dialogs.DialogDonePassword;
import com.littles.listeners.ListenerOnActivityBackClick;
import com.littles.utils.UtilIMSI;
import com.littles.utils.UtilToast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityDone extends Activity {

	private Button mTitleBackBtn;
	public static Button mStartBtn;
	public static Context instance;
	private TextView mTextPass;
	private TextView mTextNum;
	private boolean isStart = false;
	private boolean isPass = false;
	private boolean isNum = false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);     
        
        instance = ActivityDone.this;
        
        if(!"".equals(DataDone.readDonePassword(getApplicationContext()))){
        	Intent intent = new Intent(ActivityDone.this, DialogCheckPassword.class);
        	this.startActivity(intent);
        }
        
        init1();
        addListeners();
        
    }
	
	private void init2() {
		
		if("".equals(DataDone.readDoneSubID(getApplicationContext()))){
			String subID = UtilIMSI.getSubscriberID(getApplicationContext());
			DataDone.saveDoneSubID(getApplicationContext(), subID);
			System.out.println(subID);
		}
		
		if(!"".equals(DataDone.readDonePassword(getApplicationContext()))){
			isPass = true;
			mTextPass.setText(this.getResources().getString(R.string.text_done_pass_modify));
		}else{
			mTextPass.setText(this.getResources().getString(R.string.text_done_pass_setting));
		}
		
		if(!"".equals(DataDone.readDoneNumber(getApplicationContext()))){
			isNum = true;
			mTextNum.setText(this.getResources().getString(R.string.text_done_num_modify));
		}else{
			mTextNum.setText(this.getResources().getString(R.string.text_done_num_setting));
		}
		
		if(isPass == true && isNum == true){						
			mStartBtn.setEnabled(true);
			
		}else{			
			mStartBtn.setEnabled(false);
			
		}
		
		if(DataDone.readDoneStart(getApplicationContext())){
			isStart = true;
			setButtonStyleClose();
			
		}else{
			isStart = false;
			setButtonStyleStart();
		}
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		init2();
	}

	
	private void addListeners() {

		mTitleBackBtn.setOnClickListener(new ListenerOnActivityBackClick(ActivityDone.this));
		
	}

	private void init1() {

		mTitleBackBtn = (Button)findViewById(R.id.mTitleBtnBack);
		mStartBtn = (Button)findViewById(R.id.mDoneBtnStart);
		mTextPass = (TextView)findViewById(R.id.mTextPass);
		mTextNum = (TextView)findViewById(R.id.mTextNum);

		
		
	}
	
	//---【一键开启】按钮被点击
	public void onButtonStartClick(View v){
		
		if(isStart){
			isStart = false;
			setButtonStyleStart();
			DataDone.saveDoneStart(getApplicationContext(), false);
			UtilToast.showToast(ActivityDone.this, 
					this.getResources().getString(R.string.toast_done_closed), 
					DataApplication.TOAST_SERVICE_CLOSEED);
		}else{
			isStart = true;
			setButtonStyleClose();
			DataDone.saveDoneStart(getApplicationContext(), true);
			UtilToast.showToast(ActivityDone.this, 
					this.getResources().getString(R.string.toast_done_started), 
					DataApplication.TOAST_SERVICE_STARTED);
		}
				
	}

	//---【设置安全号码】
	public void onLayoutNumberClick(View v){
		
		Intent intent = new Intent(ActivityDone.this, DialogDoneNumber.class);
		ActivityDone.this.startActivity(intent);
		
	}
	//---【设置进入密码】
	public void onLayoutPasswordClick(View v){
		
		//---设置密码的Dialog(其实是一个Activity)
		Intent intent = new Intent(ActivityDone.this, DialogDonePassword.class);
		ActivityDone.this.startActivity(intent);
		
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//---当点击【返回】键时
		if(keyCode == KeyEvent.KEYCODE_BACK){
			
			ListenerOnActivityBackClick.onMyClick(ActivityDone.this);
			
		}				
		
		return false;
	}
	
	public static Context getInstance(){
		
		return instance;
		
	}
	
	private void setButtonStyleStart() {
		mStartBtn.setBackgroundResource(R.drawable.selector_btn_style_green);
		mStartBtn.setText(this.getResources().getString(R.string.btn_text_done_start));
		mStartBtn.setPadding(0, 18, 0, 18);
	}

	private void setButtonStyleClose() {
		mStartBtn.setBackgroundResource(R.drawable.selector_btn_style_red);
		mStartBtn.setText(this.getResources().getString(R.string.btn_text_done_close));
		mStartBtn.setPadding(0, 18, 0, 18);
	}
	
}
