package com.ist.nearby.ui.adapter;

public class SectionItem implements Item {
	
	private final String description;
	
	/**
     * A section contains a title - Public Transport Type, Restaurant Type, Interest Point Type
     * 
     * @param description the description chosen for the purpose
     *
     */
	public SectionItem(String description) {
		this.description = description;
	}

	/**
     * Returns the description
     * @return the description
     */
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isSection() {
		return true;
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
		return false;
	}
}
