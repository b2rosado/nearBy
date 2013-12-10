package com.example.nearby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RestaurantsFilterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_filter);
	}

	public void changeAlwaysAlertStatus(View v){
		TextView alwaysAlertStatus= (TextView) findViewById(R.id.status_alwaysAlert);
		
		if(alwaysAlertStatus.getText().equals(getResources().getText(R.string.sound)))
			alwaysAlertStatus.setText(getResources().getText(R.string.vibration));
		else if(alwaysAlertStatus.getText().equals(getResources().getText(R.string.vibration)))
			alwaysAlertStatus.setText(getResources().getText(R.string.vibration_and_sound));
		else if(alwaysAlertStatus.getText().equals(getResources().getText(R.string.vibration_and_sound)))
			alwaysAlertStatus.setText(getResources().getText(R.string.sound));
	}
	
	public void changeOnlyPromotionStatus(View v){
		System.out.println("changeOnlyPromotionsStatus called");
		TextView onlyPromotionsStatus = (TextView) findViewById(R.id.status_onlyPromotions);

		if(onlyPromotionsStatus.getText().equals(getResources().getText(R.string.sound)))
			onlyPromotionsStatus.setText(getResources().getText(R.string.vibration));
		else if(onlyPromotionsStatus.getText().equals(getResources().getText(R.string.vibration)))
			onlyPromotionsStatus.setText(getResources().getText(R.string.vibration_and_sound));
		else if(onlyPromotionsStatus.getText().equals(getResources().getText(R.string.vibration_and_sound)))
			onlyPromotionsStatus.setText(getResources().getText(R.string.sound));
	}

}
