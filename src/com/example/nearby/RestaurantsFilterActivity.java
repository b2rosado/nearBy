package com.example.nearby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RestaurantsFilterActivity extends Activity {
	private static int STATIC_INTEGER_VALUE = 0;
	private TextView alert_label;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_filter);
	}

	public void changeAlwaysAlertStatus(View v){
		alert_label = (TextView) findViewById(R.id.status_alwaysAlert);
		Intent myIntent = new Intent(this, AlertTypeActivity.class);
		myIntent.putExtra("alert", alert_label.getText());
		startActivityForResult(myIntent, STATIC_INTEGER_VALUE);
	}
	
	public void changeOnlyPromotionStatus(View v){
		alert_label = (TextView) findViewById(R.id.status_onlyPromotions);
		Intent myIntent = new Intent(this, AlertTypeActivity.class);
		myIntent.putExtra("alert", alert_label.getText());
		startActivityForResult(myIntent, STATIC_INTEGER_VALUE);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data); 
		if(requestCode == STATIC_INTEGER_VALUE && resultCode == Activity.RESULT_OK) {
			alert_label.setText(data.getStringExtra("ALERT_TYPE"));
		} 
	}
}