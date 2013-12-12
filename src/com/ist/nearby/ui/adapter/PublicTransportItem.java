package com.ist.nearby.ui.adapter;

import com.ist.nearby.domain.PublicTransport;

public class PublicTransportItem implements Item {

	private final PublicTransport publicTransport;
	
	/**
	 * A PublicTransport item contains only a {@link PublicTransport}.
	 * @param publicTransport the {@link PublicTransport} to be displayed.
	 */
	public PublicTransportItem(PublicTransport publicTransport) {
		this.publicTransport = publicTransport;
	}
	
	/**
	 * @return this item's {@link PublicTransport}.
	 */
	public PublicTransport getPublicTransport() {
		return publicTransport;
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
		return true;
	}

	@Override
	public boolean isInterestPoint() {
		return false;
	}

	@Override
	public boolean isRestaurant() {
		return false;
	}
}
