package com.ist.nearby.ui.activity;

import com.example.nearby.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class RestaurantsFilterActivity extends Activity {
	private static int STATIC_INTEGER_VALUE = 0;
	private RadioButton alwaysAlert_btn;
	private RadioButton onlyPromotions_btn;
	private RadioButton rb;
	private String alwaysAlertStatus;
	private String onlyPromotionStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_filter);
		alwaysAlert_btn = (RadioButton) findViewById(R.id.radio_alwaysAlert);
		onlyPromotions_btn = (RadioButton) findViewById(R.id.radio_onlyPromotions);
		
		//carrega o estado por definição (não é persistente)
		alwaysAlertStatus = getResources().getString(R.string.vibration);
		onlyPromotionStatus = getResources().getString(R.string.sound);
		
		alwaysAlert_btn.setText(getResources().getString(R.string.alwaysAlert) +"\n"+ alwaysAlertStatus);
		onlyPromotions_btn.setText(getResources().getString(R.string.onlyPromotions) +"\n"+ onlyPromotionStatus);
	}

	public void changeAlwaysAlertStatus(View v){
		rb = alwaysAlert_btn;
		Intent myIntent = new Intent(this, AlertTypeActivity.class);
		myIntent.putExtra("alert", rb.getText());
		startActivityForResult(myIntent, STATIC_INTEGER_VALUE);
	}
	
	public void changeOnlyPromotionStatus(View v){
		rb = onlyPromotions_btn;
		Intent myIntent = new Intent(this, AlertTypeActivity.class);
		myIntent.putExtra("alert", rb.getText());
		startActivityForResult(myIntent, STATIC_INTEGER_VALUE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data); 
		if(requestCode == STATIC_INTEGER_VALUE && resultCode == Activity.RESULT_OK) {
			rb.setText(rb.getText().toString().split("\n")[0] +"\n"+ data.getStringExtra("ALERT_TYPE"));
		} 
	}
}