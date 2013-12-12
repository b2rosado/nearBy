package com.ist.nearby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nearby.R;
import com.ist.nearby.domain.Restaurant;
import com.ist.nearby.storage.NearByDBAdapter;

public class RateActivity extends Activity {
	private TextView mText;
	private RatingBar mRate;
	
	private Restaurant mRestaurant;
	private NearByDBAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate);
		
		if(getIntent().getExtras() != null) {
			mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
			mRestaurant = mDbHelper.fetchRestaurant(getIntent().getExtras().getString("LOCAL_NAME_ID"));
			mDbHelper.close();
		}		
		mText = (TextView) findViewById(R.id.tv_text);
		mRate = (RatingBar) findViewById(R.id.ratingBarClicable);
		
		mText.setText("Atribua aqui a classificação\ndo ponto de interesse:\n"+mRestaurant.getName());
	}

	public void rate(View v){
		mRate.getRating();
		//actualizar BD com o voto
		finish();
	}

}
