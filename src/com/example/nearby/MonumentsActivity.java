package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MonumentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monuments);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.monuments, menu);
		return true;
	}
	
	public void openMonumentsInfo(View v){
		Intent myIntent = new Intent(this, MonumentsInfoActivity.class);
		startActivity(myIntent);
		
	}
}
