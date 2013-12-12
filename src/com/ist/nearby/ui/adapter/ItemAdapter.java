package com.ist.nearby.ui.adapter;

import java.util.ArrayList;

import com.example.nearby.R;
import com.ist.nearby.domain.InterestPoint;
import com.ist.nearby.domain.PublicTransport;
import com.ist.nearby.domain.Restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Item> items;
	
	public ItemAdapter(Context context, ArrayList<Item> items) {
		this.context = context;
		this.items = items;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Item getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Item item = getItem(position);
		
		if(item.isSection()) {
			
			SectionItem si = (SectionItem) item;
			convertView = layoutInflater.inflate(R.layout.list_item_section, null);
			
			//Disable all touch listeners
            convertView.setOnClickListener(null);
            convertView.setOnLongClickListener(null);
            convertView.setLongClickable(false);
            
            final TextView sectionTitle = (TextView) convertView.findViewById(R.id.tv_item_section_title);
            sectionTitle.setText(si.getDescription());
			
		} else if (item.isPublicTransport()) {
			
			PublicTransportItem publicTransportItem = (PublicTransportItem) item;
			PublicTransport publicTransport = publicTransportItem.getPublicTransport();
			
			convertView = layoutInflater.inflate(R.layout.list_item_entry, null);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView timeLeft = (TextView) convertView.findViewById(R.id.tv_info);
			
			name.setText(publicTransport.getCompany());
			timeLeft.setText(publicTransport.getSchedule());
			
		} else if (item.isInterestPoint()) {
			
			InterestPointItem interestPointItem = (InterestPointItem) item;
			InterestPoint interestPoint = interestPointItem.getInterestPoint();
			
			convertView = layoutInflater.inflate(R.layout.list_item_entry, null);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView rating = (TextView) convertView.findViewById(R.id.tv_info);
			
			if(interestPoint.getName().length() < 20) {
				name.setText(interestPoint.getName());
			} else {
				name.setText(interestPoint.getName().substring(0, 20) + "...");
			}
			
			rating.setText(interestPoint.getRating() + context.getString(R.string.rating_max));
			
		} else if (item.isRestaurant()) {
			
			RestaurantItem restaurantItem = (RestaurantItem) item;
			Restaurant restaurant = restaurantItem.getRestaurant();
			
			convertView = layoutInflater.inflate(R.layout.list_item_entry, null);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView rating = (TextView) convertView.findViewById(R.id.tv_info);
			
			name.setText(restaurant.getName());
			rating.setText(restaurant.getRating() + context.getString(R.string.rating_max));
		}
		
		return convertView;
	}
}
