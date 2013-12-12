package com.ist.nearby.ui.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.nearby.R;
import com.ist.nearby.domain.InterestPoint;
import com.ist.nearby.storage.NearByDBAdapter;
import com.ist.nearby.ui.adapter.InterestPointItem;
import com.ist.nearby.ui.adapter.Item;
import com.ist.nearby.ui.adapter.ItemAdapter;
import com.ist.nearby.ui.adapter.SectionItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class InterestPointsActivity extends Activity {

	private ListView mInterestPoints;
	private NearByDBAdapter mDbHelper;
	private SparseArray<InterestPoint> mItemToInterestPoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interest_points);
		
		mInterestPoints = (ListView) findViewById(R.id.lv_content);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setupListeners();
		updateInterestPoints(fetchInterestPoints());
	}
	
	private ArrayList<InterestPoint> fetchInterestPoints() {
		
		ArrayList<InterestPoint> interestPoints;
		mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
		interestPoints = mDbHelper.fetchInterestPoints();
		
		mDbHelper.close();
		
		return interestPoints;
	}
	
	private void updateInterestPoints(ArrayList<InterestPoint> interestPoints) {
		
		ArrayList<Item> items = new ArrayList<Item>();
		mItemToInterestPoint = new SparseArray<InterestPoint>();
		Collections.sort(interestPoints, new InterestPointTypeComparator());
		
		//Initial interestPoint type to set the cycle comparison
		String auxType = interestPoints.get(0).getType();
		
		items.add(new SectionItem(auxType));
		
		int index = 0;
		
		for(InterestPoint interestPoint : interestPoints) {
			
			String type = interestPoint.getType();
			
			if(!type.equals(auxType)) {
				items.add(new SectionItem(type));
				auxType = type;
				index++;
			}
			
			index++;
			mItemToInterestPoint.put(index, interestPoint);
			items.add(new InterestPointItem(interestPoint));
		}
		
		ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), items);
		mInterestPoints.setAdapter(itemAdapter);	
	}
	
	class InterestPointTypeComparator implements Comparator<InterestPoint> {

		@Override
		public int compare(InterestPoint interestPoint1, InterestPoint interestPoint2) {
			return interestPoint1.getType().compareTo(interestPoint2.getType());
		}
	}
	
	private void setupListeners() {
		
		mInterestPoints.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				
				InterestPoint interestPoint = mItemToInterestPoint.get(position);
				Intent intent = new Intent(getApplicationContext(), InterestPointsInfoActivity.class);
				intent.putExtra("INTEREST_POINT_NAME_ID", interestPoint.getName());
				startActivity(intent);
			}
		});
	}
}
