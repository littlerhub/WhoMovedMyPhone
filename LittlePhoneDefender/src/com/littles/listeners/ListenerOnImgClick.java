package com.littles.listeners;

import com.littles.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ListenerOnImgClick implements OnClickListener {

	private Activity mThis;
	private static boolean flag1 = true;
	private static boolean flag2 = true;
	private static boolean flag3 = true;
	private Animation animation;
	
	public ListenerOnImgClick(Activity mThis){
		this.mThis = mThis;
	}
	
	public void onClick(View v) {

		switch(v.getId()){
		
			case R.id.iv_pocket:{
				if(flag1){
					v.setBackgroundResource(R.drawable.bg_guarder_pocket_detail);
					flag1 = false;
				}else{
					v.setBackgroundResource(R.drawable.bg_guarder_pocket);
					flag1 = true;
				}				
				break;
				
			}case R.id.iv_hand:{
				if(flag2){
					v.setBackgroundResource(R.drawable.bg_guarder_hand_detail);
					flag2 = false;
				}else{
					v.setBackgroundResource(R.drawable.bg_guarder_hand);
					flag2 = true;
				}			
				break;
				
			}case R.id.iv_away:{
				if(flag3){
					v.setBackgroundResource(R.drawable.bg_guarder_away_detail);
					flag3 = false;
				}else{
					v.setBackgroundResource(R.drawable.bg_guarder_away);
					flag3 = true;
				}				
				break;
				
			}
			
		}
		
		animation = AnimationUtils.loadAnimation(mThis, R.anim.img_change);
		v.startAnimation(animation);
		
		
	}

}
