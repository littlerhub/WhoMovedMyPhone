package com.littles.anims;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
/**
 * ���������ƶ��Ķ���
 * @author LittleBoy
 * 2013-05-05 16:45 
 */
public class AnimMove {
	
	private View view;//--- ��Ҫ���ض����Ŀؼ�
	private TranslateAnimation leftAnim;
	private TranslateAnimation rightAnim;

	public AnimMove(View v){
		
		this.view = v;
		
	}
	
	//---���ƶ��������ƶ���
	public Animation getAnim(){
		
		rightAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.3f,
				Animation.RELATIVE_TO_PARENT, -1.5f,
				Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
				0f);
		leftAnim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.5f,
				Animation.RELATIVE_TO_PARENT, -0.3f,
				Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT,
				0f);
		//---����ʱ��
		rightAnim.setDuration(25000);
		leftAnim.setDuration(25000);
		//---ͣ�ڶ���������Ļ���
		rightAnim.setFillAfter(true);
		leftAnim.setFillAfter(true);

		rightAnim.setAnimationListener(new Animation.AnimationListener() {
			
			public void onAnimationStart(Animation animation) {}
			
			public void onAnimationRepeat(Animation animation) {}

			//---�����ƶ��������������������ƶ���
			public void onAnimationEnd(Animation animation) {

				view.startAnimation(leftAnim);
				
			}
		});
		
		
		leftAnim.setAnimationListener(new Animation.AnimationListener() {
			
			public void onAnimationStart(Animation animation) {}
			
			public void onAnimationRepeat(Animation animation) {}

			//---�����ƶ��������������������ƶ���
			public void onAnimationEnd(Animation animation) {

				view.startAnimation(rightAnim);
				
			}
		});

		return leftAnim;
	}
	
}
