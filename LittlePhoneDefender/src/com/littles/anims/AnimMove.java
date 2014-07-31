package com.littles.anims;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
/**
 * 左右来回移动的动画
 * @author LittleBoy
 * 2013-05-05 16:45 
 */
public class AnimMove {
	
	private View view;//--- 需要加载动画的控件
	private TranslateAnimation leftAnim;
	private TranslateAnimation rightAnim;

	public AnimMove(View v){
		
		this.view = v;
		
	}
	
	//---左移动画、右移动画
	public Animation getAnim(){
		
		rightAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.3f,
				Animation.RELATIVE_TO_PARENT, -1.5f,
				Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
				0f);
		leftAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.5f,
				Animation.RELATIVE_TO_PARENT, -0.3f,
				Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
				0f);
		//---持续时间
		rightAnim.setDuration(25000);
		leftAnim.setDuration(25000);
		//---停在动画结束后的画面
		rightAnim.setFillAfter(true);
		leftAnim.setFillAfter(true);

		rightAnim.setAnimationListener(new Animation.AnimationListener() {
			
			public void onAnimationStart(Animation animation) {}
			
			public void onAnimationRepeat(Animation animation) {}

			//---当右移动画结束，立即开启左移动画
			public void onAnimationEnd(Animation animation) {

				view.startAnimation(leftAnim);
				
			}
		});
		
		
		leftAnim.setAnimationListener(new Animation.AnimationListener() {
			
			public void onAnimationStart(Animation animation) {}
			
			public void onAnimationRepeat(Animation animation) {}

			//---当左移动画结束，立即开启右移动画
			public void onAnimationEnd(Animation animation) {

				view.startAnimation(rightAnim);
				
			}
		});

		return leftAnim;
	}
	
}
