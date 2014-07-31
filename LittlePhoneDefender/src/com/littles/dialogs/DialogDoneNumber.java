package com.littles.dialogs;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.littles.*;
import com.littles.activitys.ActivityDone;
import com.littles.datas.DataDone;
import com.littles.utils.UtilSms;
import com.littles.utils.UtilString;

public class DialogDoneNumber extends Activity {

	private EditText mEditTextNum = null;
	private TextView mTitleNum = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_done_number);
		
		mTitleNum = (TextView)findViewById(R.id.mTitleDialogNum);
		mEditTextNum = (EditText)findViewById(R.id.mDoneNumber);
		
		if(!"".equals(DataDone.readDoneNumber(getApplicationContext()))){
			
			mTitleNum.setText(this.getResources().getString(R.string.text_done_num_dialog_modify));
			mEditTextNum.setText(DataDone.readDoneNumber(getApplicationContext()));
		}else{
			mTitleNum.setText(this.getResources().getString(R.string.text_done_num_dialog_setting));
		}

	}

	public void onDialogDoneLayoutClick(View v){
		
		Toast.makeText(getApplicationContext(), "提示：点击窗口外部即可关闭！", Toast.LENGTH_SHORT).show();	
		
	}
	
	
	public boolean onTouchEvent(MotionEvent event){
		this.finish();
		return true;
	}
	
	//---点击Button："确认"
	public void onButtonNumberConfirm(View v) {
		
		String number = mEditTextNum.getText().toString();
		
		if(!isEmpty(number)){
			
			DataDone.saveDoneNumber(getApplicationContext(), number);
			if(!"".equals(DataDone.readDonePassword(getApplicationContext()))){
				ActivityDone.mStartBtn.setEnabled(true);
			}else{
				ActivityDone.mStartBtn.setEnabled(false);
			}
			
			//---提示“设置成功：number”
			Toast.makeText(getApplicationContext(), 
					this.getResources().getString(R.string.toast_done_num_success) + number, 
					Toast.LENGTH_SHORT).show();

			this.finish();
			
		}		
  
	}  
	
	//---点击Button："测试"
	public void onButtonNumberTest(View v) { 
		
		String number = mEditTextNum.getText().toString();
		
		if(!isEmpty(number)){
			
			//---发送短信用来测试
			UtilSms.sendSms(number, this.getResources().getString(R.string.text_done_sms3), this);
			Toast.makeText(this, this.getResources().getString(R.string.text_done_sms4), Toast.LENGTH_SHORT).show();
		}
		
	}  
	
	public boolean isEmpty(String number){
		
		boolean flag = false;
		
		if(UtilString.isEmpty(number)){
			
			flag = true;
			
			Toast.makeText(getApplicationContext(), 
					this.getResources().getString(R.string.toast_done_num_empty), 
					Toast.LENGTH_SHORT).show();
			
		}		
    	    	
		return flag;
		
	}
	
}
