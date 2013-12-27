package com.ist.nearby.ui.activity;

import com.example.nearby.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class FiltersTypeActivity extends Activity {
	private final String FILTER_TYPE = "FILTER_TYPE";
	private final String TRANSPORTS = "TRANSPORTS";
	private final String RESTAURANTS = "RESTAURANTS";
	private final String INTEREST_POINTS = "INTEREST POINTS";
	private static int STATIC_INTEGER_VALUE = 0;
	private RadioButton alwaysAlert_btn;
	private RadioButton onlyPromotions_btn;
	private RadioButton rb;
	private String alwaysAlertStatus;
	private String onlyPromotionStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filters_type);
		
		TextView label = (TextView) findViewById(R.id.lbl_statusbar);
		
		if(getIntent().getExtras().get(FILTER_TYPE).equals(TRANSPORTS))
			label.setText(R.string.transports_filters);
		else if(getIntent().getExtras().get(FILTER_TYPE).equals(RESTAURANTS))
			label.setText(R.string.restaurants_filters);
		else if(getIntent().getExtras().get(FILTER_TYPE).equals(INTEREST_POINTS))
			label.setText(R.string.interest_points_filters);
		
		
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