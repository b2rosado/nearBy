package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransportsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transports);
	}
	
	public void openTransportInfo(View v){
		Intent myIntent = new Intent(this, TransportInfoActivity.class);
		startActivity(myIntent);
	}

}
