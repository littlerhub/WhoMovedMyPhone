package com.littles.listeners;

import com.littles.utils.UtilService;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ListenerOnServiceStartClick implements OnClickListener {

	private Activity mThis;
	private String type;
		
	public ListenerOnServiceStartClick(Activity mThis, String type){
		
		this.mThis = mThis;
		this.type = type;		
		
	}	
	
	public void onClick(View v) {
				
		UtilService.startService(mThis, type);

	}
	   
}
