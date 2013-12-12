package com.ist.nearby.ui.adapter;

import com.ist.nearby.domain.InterestPoint;

public class InterestPointItem implements Item {

	private final InterestPoint interestPoint;
	
	/**
	 * A InterestPoint item contains only a {@link InterestPoint}.
	 * @param interestPoint the {@link InterestPoint} to be displayed.
	 */
	public InterestPointItem(InterestPoint interestPoint) {
		this.interestPoint = interestPoint;
	}
	
	/**
	 * @return this item's {@link InterestPoint}.
	 */
	public InterestPoint getInterestPoint() {
		return interestPoint;
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
		return true;
	}

	@Override
	public boolean isRestaurant() {
		return false;
	}
}
