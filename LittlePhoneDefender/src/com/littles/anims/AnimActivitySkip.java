package com.littles.anims;

import com.littles.*;

import android.app.Activity;
/**
 * 当Activity跳转时的动画
 * @author LittleBoy
 * 2013-05-06 00:28
 *
 */
public class AnimActivitySkip {

	public void getAnimEnter(Activity mThis) {
		
		if(mThis != null){
			
			mThis.overridePendingTransition(R.anim.activity_enter_one, R.anim.activity_enter_two);
			
		}
	
	}
	
	public void getAnimExit(Activity mThis) {
		
		if(mThis != null){
			
			mThis.overridePendingTransition(R.anim.activity_exit_two, R.anim.activity_exit_one);
			
		}
	
	}
	
}
