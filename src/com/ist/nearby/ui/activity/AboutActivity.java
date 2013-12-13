package com.ist.nearby.ui.activity;

import com.example.nearby.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AboutActivity extends Activity {
	private int logoCount = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
	}
	
	public void logoClicked(View v){
		if(logoCount++ == 30){
			logoCount=0;
			RelativeLayout screen = (RelativeLayout) findViewById(R.id.smartwatch_screen);			
			screen.setBackground(getResources().getDrawable(R.drawable.zorro));
		}
			
	}
}
