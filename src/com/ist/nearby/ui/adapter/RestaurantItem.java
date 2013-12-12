package com.ist.nearby.ui.adapter;

import com.ist.nearby.domain.Restaurant;

public class RestaurantItem implements Item {
	
	private final Restaurant restaurant;
	
	/**
	 * A Restaurant item contains only a {@link Restaurant}.
	 * @param restaurant the {@link Restaurant} to be displayed.
	 */
	public RestaurantItem(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	/**
	 * @return this item's {@link Restaurant}.
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	@Override
	public boolean isSection() {
		return false;
	}

	@Override
	public boolean isScroll() {
		return false;
	}

	@Override
	public boolean isPublicTransport() {
		return false;
	}

	@Override
	public boolean isInterestPoint() {
		return false;
	}

	@Override
	public boolean isRestaurant() {
		return true;
	}
}
