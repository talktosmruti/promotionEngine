package com.promotion.engine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.promotion.engine.svc.IPromotionSvc;

public class Cart {

	private Map<String, Item> items = new ConcurrentHashMap<>();
	
	private double cartTotalBeforePromotions;
	private double cartTotalAfterPromotions;
	private List<IPromotionSvc> appliedPromos = new ArrayList<IPromotionSvc>();

	public final Map<String, Item> getItems() {
		return items;
	}

	public synchronized void addItemsToCart(Item item) {
		this.items.put(item.getId(), item);
	}

	public double getCartTotalBeforePromotions() {
		return cartTotalBeforePromotions;
	}

	public void setCartTotalBeforePromotions(double cartTotalBeforePromotions) {
		this.cartTotalBeforePromotions = cartTotalBeforePromotions;
	}

	public double getCartTotalAfterPromotions() {
		return cartTotalAfterPromotions;
	}

	public void setCartTotalAfterPromotions(double cartTotalAfterPromotions) {
		this.cartTotalAfterPromotions = cartTotalAfterPromotions;
	}

	public List<IPromotionSvc> getAppliedPromos() {
		return appliedPromos;
	}

	public void addToAppliedPromos(IPromotionSvc appliedPromo) {
		this.appliedPromos.add(appliedPromo);
	}
}
