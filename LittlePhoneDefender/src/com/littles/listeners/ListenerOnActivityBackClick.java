package com.littles.listeners;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.littles.anims.AnimManager;
import com.littles.datas.DataApplication;

public class ListenerOnActivityBackClick implements OnClickListener{
	
	//�����¼���Activity����
	private Activity aThis;
	
	public ListenerOnActivityBackClick(Activity aThis){
		
		this.aThis = aThis;
		
	}

	public void onClick(View v) {

		onMyClick(aThis);
	}

	public static void onMyClick(Activity mThis){
		
		if(mThis != null){
			mThis.finish(); 
			//---ִ�ж���        
	    	AnimManager.getActivityAnim(mThis, DataApplication.ANIM_ACTIVITY_EXIT);
		}

	}
	
}
