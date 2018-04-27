package com.alpha.booking.model;

import java.util.List;

public class CategoryWithItem extends Category {

	public CategoryWithItem() {
		// TODO Auto-generated constructor stub
	}
	
	private List<SellItem> sellItems;

	public List<SellItem> getSellItems() {
		return sellItems;
	}

	public void setSellItems(List<SellItem> sellItems) {
		this.sellItems = sellItems;
	}


	

}
