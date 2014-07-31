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
		
		Toast.makeText(getApplicationContext(), "��ʾ����������ⲿ���ɹرգ�", Toast.LENGTH_SHORT).show();	
		
	}
	
	
	public boolean onTouchEvent(MotionEvent event){
		this.finish();
		return true;
	}
	
	//---���Button��"ȷ��"
	public void onButtonNumberConfirm(View v) {
		
		String number = mEditTextNum.getText().toString();
		
		if(!isEmpty(number)){
			
			DataDone.saveDoneNumber(getApplicationContext(), number);
			if(!"".equals(DataDone.readDonePassword(getApplicationContext()))){
				ActivityDone.mStartBtn.setEnabled(true);
			}else{
				ActivityDone.mStartBtn.setEnabled(false);
			}
			
			//---��ʾ�����óɹ���number��
			Toast.makeText(getApplicationContext(), 
					this.getResources().getString(R.string.toast_done_num_success) + number, 
					Toast.LENGTH_SHORT).show();

			this.finish();
			
		}		
  
	}  
	
	//---���Button��"����"
	public void onButtonNumberTest(View v) { 
		
		String number = mEditTextNum.getText().toString();
		
		if(!isEmpty(number)){
			
			//---���Ͷ�����������
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
