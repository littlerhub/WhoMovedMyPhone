package com.littles.listeners;

import com.littles.utils.UtilService;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ListenerOnServiceCloseClick implements OnClickListener {
	
	private Activity mThis;
	private String type;	
	
	public ListenerOnServiceCloseClick(Activity mThis, String type){
		
		this.mThis = mThis;
		this.type = type;
		
	}	
	
	public void onClick(View v) {
		// 关闭指定的服务
		UtilService.closeService(mThis, type);
		
	}

}
