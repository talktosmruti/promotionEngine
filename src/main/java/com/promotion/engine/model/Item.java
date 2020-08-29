package com.promotion.engine.model;

public class Item {

	/*SKUID of item*/
	private String id;
	/*Is any promotions applied on this item. this is to take care of mutually exclusive promotion restrictions.*/
	private boolean isPromotionApplied;
	/*Quantities bought*/
	private int quantity;
	/*Each unit price*/
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", isPromotionApplied=" + isPromotionApplied + ", quantity=" + quantity
				+ ", unitPrice=" + unitPrice + "]";
	}
	
	
}
