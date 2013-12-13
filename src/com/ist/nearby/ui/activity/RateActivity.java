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
		if(getIntent().getExtras() != null) {
			mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
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
			mDbHelper.close();
		}		
	}

	public void rate(View v){
		float rating;
		mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
		switch(getIntent().getExtras().getInt(TYPE)){
			case RESTAURANT_TYPE:
				rating = (mRestaurant.getRating()*mRestaurant.getNumberOfVotes()+mRate.getRating())/(mRestaurant.getNumberOfVotes()+1);
				mDbHelper.updateRestaurantRating(mRestaurant.getName(), rating);
				break;
			case INTEREST_POINT_TYPE:
				rating = (mInterestPoint.getRating()*mInterestPoint.getNumberOfVotes()+mRate.getRating())/(mInterestPoint.getNumberOfVotes()+1);
				mDbHelper.updateInterestPointRating(mInterestPoint.getName(), rating);
				break;
		}
		mDbHelper.close();
		finish();
	}

}
