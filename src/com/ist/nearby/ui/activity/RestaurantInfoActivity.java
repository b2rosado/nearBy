package com.ist.nearby.ui.activity;

import com.example.nearby.R;
import com.ist.nearby.domain.Restaurant;
import com.ist.nearby.storage.NearByDBAdapter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantInfoActivity extends Activity implements SensorEventListener {
	
	private ImageView mImage;
	private TextView mName;
	
	private Restaurant mRestaurant;
	private NearByDBAdapter mDbHelper;
	
    private float currentDegree = 0f;
    private SensorManager mSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_info);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
			mRestaurant = mDbHelper.fetchRestaurant(extras.getString("RESTAURANT_NAME_ID"));
			mDbHelper.close();
		}
		
		mName = (TextView) findViewById(R.id.tv_name);	
		mImage = (ImageView) findViewById(R.id.navigation_arrow);
		
		// initialize your android device sensor capabilities
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// get the angle around the z-axis rotated
		float degree = Math.round(event.values[0]);
		
		// create a rotation animation (reverse turn degree degrees)
		RotateAnimation ra = new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		
		// how long the animation will take place
		ra.setDuration(210);
		
		// set the animation after the end of the reservation status
		ra.setFillAfter(true);
		
		// Start the animation
		mImage.startAnimation(ra);
		currentDegree = -degree;		
	}
	
	@Override
	@SuppressWarnings("deprecation")
	protected void onResume() {
	    super.onResume();
	    // for the system's orientation sensor registered listeners
	    mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
	    mName.setText(mRestaurant.getName());
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	    // to stop the listener and save battery
	    mSensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//not in use
	}

}
