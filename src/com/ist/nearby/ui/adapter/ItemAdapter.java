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
import android.widget.RatingBar;
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
			convertView.setPadding(5, 19, 5, 19);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView timeLeft = (TextView) convertView.findViewById(R.id.tv_info);
			final RatingBar rating = (RatingBar) convertView.findViewById(R.id.ratingBarListView);
			rating.setVisibility(View.GONE);
			
			name.setText(publicTransport.getCompany());
			timeLeft.setText(publicTransport.getSchedule());
			
		} else if (item.isInterestPoint()) {
			
			InterestPointItem interestPointItem = (InterestPointItem) item;
			InterestPoint interestPoint = interestPointItem.getInterestPoint();
			
			convertView = layoutInflater.inflate(R.layout.list_item_entry, null);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView schedule = (TextView) convertView.findViewById(R.id.tv_info);
			final RatingBar rating =(RatingBar) convertView.findViewById(R.id.ratingBarListView);
			
			name.setText(interestPoint.getName());
			schedule.setText(interestPoint.getSchedule());
			rating.setRating(interestPoint.getRating());
			
		} else if (item.isRestaurant()) {
			
			RestaurantItem restaurantItem = (RestaurantItem) item;
			Restaurant restaurant = restaurantItem.getRestaurant();
			
			convertView = layoutInflater.inflate(R.layout.list_item_entry, null);
			
			final TextView name = (TextView) convertView.findViewById(R.id.tv_name);
			final TextView schedule = (TextView) convertView.findViewById(R.id.tv_info);
			final RatingBar rating =(RatingBar) convertView.findViewById(R.id.ratingBarListView);
			
			name.setText(restaurant.getName());
			schedule.setText(restaurant.getSchedule());
			rating.setRating(restaurant.getRating());
		}
		
		return convertView;
	}
}
