package com.littles.anims;

import com.littles.datas.DataApplication;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

public class AnimManager {
	//---���������ƶ�
	public static Animation getBackgroundAnim(View v){
		
		return new AnimMove(v).getAnim();
	}
	
	//---Activity�����ת����
	public static void getActivityAnim(Activity mThis, int type){
		switch(type){
			case DataApplication.ANIM_ACTIVITY_ENTER:{
				new AnimActivitySkip().getAnimEnter(mThis);
				break;
			}case DataApplication.ANIM_ACTIVITY_EXIT:{
				new AnimActivitySkip().getAnimExit(mThis);
				break;
			}
		}
		
		
	}
	
}
