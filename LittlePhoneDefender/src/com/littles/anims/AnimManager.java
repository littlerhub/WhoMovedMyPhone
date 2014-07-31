package com.littles.anims;

import com.littles.datas.DataApplication;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

public class AnimManager {
	//---左右来回移动
	public static Animation getBackgroundAnim(View v){
		
		return new AnimMove(v).getAnim();
	}
	
	//---Activity间的跳转动画
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
