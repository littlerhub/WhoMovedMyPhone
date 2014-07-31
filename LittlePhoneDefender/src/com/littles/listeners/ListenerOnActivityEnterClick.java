package com.littles.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.littles.anims.AnimManager;
import com.littles.datas.DataApplication;

public class ListenerOnActivityEnterClick implements OnClickListener{
	
	//�����¼��ĵ�ǰActivity����
	private Activity aThis;
	//Ҫ��ת��Activity��Class����
	private Class<?> bClass;
	
	public ListenerOnActivityEnterClick(Activity aThis, Class<?> bClass){
		
		this.aThis = aThis;
		this.bClass = bClass;
		
	}

	public void onClick(View v) {
		
		if(aThis != null){
			myOnClick(aThis, bClass);
		}
		
	}
	
	public static void myOnClick(Activity aThis, Class<?> bClass){
		//��ת����Ӧ��Activity
		Intent intent = new Intent(aThis, bClass);
		aThis.startActivity(intent);
		AnimManager.getActivityAnim(aThis, DataApplication.ANIM_ACTIVITY_ENTER);
		
	}

}
