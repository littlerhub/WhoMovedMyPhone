package com.littles.activitys;

import com.littles.R;
import com.littles.listeners.ListenerOnActivityEnterClick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
public class ActivityMain extends Activity {

	private ImageView mDoing = null;
	private ImageView mDone = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		init();
		
		addListeners();
				
	}

	private void addListeners() {

		mDoing.setOnClickListener(new ListenerOnActivityEnterClick(ActivityMain.this, ActivityDoing.class));
		mDone.setOnClickListener(new ListenerOnActivityEnterClick(ActivityMain.this, ActivityDone.class));
		
	}

	private void init() {

		mDoing = (ImageView)findViewById(R.id.mDoing);
		mDone = (ImageView)findViewById(R.id.mDone);

	}
	
}
