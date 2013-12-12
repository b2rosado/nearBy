package com.ist.nearby.ui.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.example.nearby.R;
import com.ist.nearby.domain.PublicTransport;
import com.ist.nearby.storage.NearByDBAdapter;
import com.ist.nearby.ui.adapter.Item;
import com.ist.nearby.ui.adapter.ItemAdapter;
import com.ist.nearby.ui.adapter.PublicTransportItem;
import com.ist.nearby.ui.adapter.SectionItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TransportsActivity extends Activity {

	private ListView mTransports;
	private NearByDBAdapter mDbHelper;
	private SparseArray<PublicTransport> mItemToTransport;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transports);
		
		mTransports = (ListView) findViewById(R.id.lv_content);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setupListeners();
		updateTransports(fetchTransports());
	}
	
	private ArrayList<PublicTransport> fetchTransports() {
		
		ArrayList<PublicTransport> publicTransports;
		mDbHelper = NearByDBAdapter.getInstance(getApplicationContext());
		publicTransports = mDbHelper.fetchTransports();
		
		mDbHelper.close();
		
		return publicTransports;
	}
	
	private void updateTransports(ArrayList<PublicTransport> publicTransports) {
		
		ArrayList<Item> items = new ArrayList<Item>();
		mItemToTransport = new SparseArray<PublicTransport>();
		Collections.sort(publicTransports, new TransportTypeComparator());
		
		//Initial restaurant type to set the cycle comparison
		String auxType = publicTransports.get(0).getType();
		
		items.add(new SectionItem(auxType));
		
		int index = 0;
		
		for(PublicTransport publicTransport : publicTransports) {
			
			String type = publicTransport.getType();
			
			if(!type.equals(auxType)) {
				items.add(new SectionItem(type));
				auxType = type;
				index++;
			}
			
			index++;
			mItemToTransport.put(index, publicTransport);
			items.add(new PublicTransportItem(publicTransport));
		}
		
		ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), items);
		mTransports.setAdapter(itemAdapter);	
	}
	
	class TransportTypeComparator implements Comparator<PublicTransport> {

		@Override
		public int compare(PublicTransport publicTransport1, PublicTransport publicTransport2) {
			return publicTransport1.getType().compareTo(publicTransport2.getType());
		}
	}
	
	private void setupListeners() {
		
		mTransports.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				
				PublicTransport publicTransport = mItemToTransport.get(position);
				Intent intent = new Intent(getApplicationContext(), TransportInfoActivity.class);
				intent.putExtra("TRANSPORTS_ID", publicTransport.getId());
				startActivity(intent);
			}
		});
	}
}
