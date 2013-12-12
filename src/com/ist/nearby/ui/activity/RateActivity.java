package com.ist.nearby.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nearby.R;
import com.ist.nearby.domain.InterestPoint;
import com.ist.nearby.domain.Restaurant;
import com.ist.nearby.storage.NearByDBAdapter;

public class RateActivity extends Activity {
	private final int RESTAURANT_TYPE = 0;
	private final int INTEREST_POINT_TYPE = 1;
	private final String TYPE = "OBJECT_TYPE";
	private final String ID = "NAME_ID";
	
	private TextView mText;
	private RatingBar mRate;
	
	private Restaurant mRestaurant;
	private InterestPoint mInterestPoint;
	private NearByDBAdapter mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate);
		mText = (TextView) findViewById(R.id.tv_text);
		mRate = (RatingBar) findViewById(R.id.ratingBarClicable);
		System.out.println(1);
		if(getIntent().getExtras() != null) {
			mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
			System.out.println(2);
			switch(getIntent().getExtras().getInt(TYPE)){
				case RESTAURANT_TYPE:			
					mRestaurant = mDbHelper.fetchRestaurant(getIntent().getExtras().getString(ID));
					mText.setText("Atribua aqui a classificação\ndo ponto de interesse:\n"+mRestaurant.getName());
					break;
				case INTEREST_POINT_TYPE:
					mInterestPoint = mDbHelper.fetchInterestPoint(getIntent().getExtras().getString(ID));
					mText.setText("Atribua aqui a classificação\ndo ponto de interesse:\n"+mInterestPoint.getName());
					break;
			}
			System.out.println(3);
			mDbHelper.close();
			System.out.println(4);
		}		
	}

	public void rate(View v){
		mRate.getRating();
		//actualizar BD com o voto
		finish();
	}

}
