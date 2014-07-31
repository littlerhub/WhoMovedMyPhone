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
import com.littles.utils.UtilPassword;

public class DialogDonePassword extends Activity {

	private TextView mTitlePass = null;
	private EditText mEditTextPass = null;
	private EditText mEditTextConfirm = null;	
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_done_password);

		mTitlePass = (TextView)findViewById(R.id.mTitleDialogPass);
		mEditTextPass = (EditText)findViewById(R.id.mDonePassword);
		mEditTextConfirm = (EditText)findViewById(R.id.mDonePasswordConfirm);
		
		if(!"".equals(DataDone.readDonePassword(getApplicationContext()))){
			
			mTitlePass.setText(this.getResources().getString(R.string.text_done_pass_dialog_modify));
		}else{
			mTitlePass.setText(this.getResources().getString(R.string.text_done_pass_dialog_setting));
		}
		
	}

	public void onDialogDoneLayoutClick(View v){
		
		Toast.makeText(getApplicationContext(), "��ʾ����������ⲿ���ɹرգ�", Toast.LENGTH_SHORT).show();	
		
	}
	
	public boolean onTouchEvent(MotionEvent event){
		
		this.finish();
		return true;
		
	}
	
	//---���Button��"��"
	public void onButtonPasswordPositive(View v) {  
		
    	String first = mEditTextPass.getText().toString();
    	String confirm = mEditTextConfirm.getText().toString();
    	
    	if(UtilPassword.isOk(first, confirm) == 1){
    		
    		DataDone.saveDonePassword(getApplicationContext(), first);
    		//---��ʾ���������óɹ�����
    		Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.toast_done_pass_success), Toast.LENGTH_SHORT).show();
    		
    		if(!"".equals(DataDone.readDoneNumber(getApplicationContext()))){
				ActivityDone.mStartBtn.setEnabled(true);
			}else{
				ActivityDone.mStartBtn.setEnabled(false);
			}
    		
    		this.finish();
    		
    	}else if(UtilPassword.isOk(first, confirm) == 0){
    		
    		Toast.makeText(getApplicationContext(), 
    				this.getResources().getString(R.string.toast_done_pass_empty), 
    				Toast.LENGTH_SHORT).show();
    		
    	}else if(UtilPassword.isOk(first, confirm) == -1){
    		
    		Toast.makeText(getApplicationContext(), 
    				this.getResources().getString(R.string.toast_done_pass_different), 
    				Toast.LENGTH_SHORT).show();
    		
    	}
    	
    }  
	
	
	//---���Button��"��"
	public void onButtonPasswordNegative(View v) { 
		
		this.finish();    	
		
	}  
	
}
