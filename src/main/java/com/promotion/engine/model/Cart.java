package com.promotion.engine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.promotion.engine.svc.IPromotionSvc;

public class Cart {

	/*Map having item Id vs Item*/
	private Map<String, Item> items = new ConcurrentHashMap<>();

	/*Total payable Amount for the shopping*/
	private double cartTotalAfterPromotions;
	
	/*This list can be used to hold the all the promotions applied on the cart. This has not been implemented now.*/
	private List<IPromotionSvc> appliedPromos = new ArrayList<IPromotionSvc>();

	public final Map<String, Item> getItems() {
		return items;
	}

	public synchronized void addItemsToCart(Item item) {
		this.items.put(item.getId(), item);
	}

	public double getCartTotalAfterPromotions() {
		return cartTotalAfterPromotions;
	}

	public void incrementCartPrice(double cartTotalAfterPromotions) {
		this.cartTotalAfterPromotions += cartTotalAfterPromotions;
	}

	public List<IPromotionSvc> getAppliedPromos() {
		return appliedPromos;
	}

	public void addToAppliedPromos(IPromotionSvc appliedPromo) {
		this.appliedPromos.add(appliedPromo);
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + ", cartTotalAfterPromotions=" + cartTotalAfterPromotions + ", appliedPromos="
				+ appliedPromos + "]";
	}
	
	
}
