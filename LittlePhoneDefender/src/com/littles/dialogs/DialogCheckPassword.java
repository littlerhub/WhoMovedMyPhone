package com.littles.dialogs;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.littles.*;
import com.littles.activitys.ActivityDone;
import com.littles.datas.DataDone;
import com.littles.listeners.ListenerOnActivityBackClick;
import com.littles.utils.UtilString;

public class DialogCheckPassword extends Activity {

	private EditText mEditTextPass = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_check_password);
		

		mEditTextPass = (EditText)findViewById(R.id.mCheckPassword);
		
	}

	
	//---点击Button："确定"
	public void onButtonPasswordConfirm(View v) {  
		
    	String password = mEditTextPass.getText().toString();
    	
    	if(UtilString.isEmpty(password)){
    		
    		Toast.makeText(getApplicationContext(), 
    				this.getResources().getString(R.string.toast_done_pass_empty), 
    				Toast.LENGTH_SHORT).show();
    		
    	}else if(password.equals(DataDone.readDonePassword(getApplicationContext()))){
    		   	
    		this.finish();
    		
    	}else{
    		
    		Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.toast_check_pass_err), Toast.LENGTH_SHORT).show();
    	
    	}
    	
    }  
	
	//---点击Button："返回"
	public void onButtonPasswordBack(View v) { 
		
		myFinish();
		
	}  
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//---当点击【返回】键时
		if(keyCode == KeyEvent.KEYCODE_BACK){
			
			myFinish();
			
		}				
		
		return false;
	}
	
	public void myFinish(){
		 			
		ListenerOnActivityBackClick.onMyClick(this);
		ListenerOnActivityBackClick.onMyClick((ActivityDone) ActivityDone.getInstance());
		
	}
	
}
