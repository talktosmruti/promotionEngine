package com.promotion.engine.model;

public class Item {

	private String id;
	private boolean isPromotionApplied;
	private int quantity;
	private double unitPrice;

	public Item(String item, int quantity, double unitPrice) {
		super();
		this.id = item;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String item) {
		this.id = item;
	}

	public boolean isPromotionApplied() {
		return isPromotionApplied;
	}

	public void setPromotionApplied(boolean isPromotionApplied) {
		this.isPromotionApplied = isPromotionApplied;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void removeQuantityForWhichPromotionApplied(int quantityEligibleForPrommotion) {
		this.quantity -= quantityEligibleForPrommotion;
	}
}
