package com.promotion.engine.util;

import java.util.List;

import com.promotion.engine.model.Cart;
import com.promotion.engine.svc.IPromotionSvc;
import com.promotion.engine.svc.PromotionBuilder;

public class CartCheckOutSvc {

	private PromotionBuilder builder;
	private int maxAllowedPromos;

	public CartCheckOutSvc(PromotionBuilder builder, int maxAllowedPromos) {
		this.builder = builder;
		this.maxAllowedPromos = Math.min(maxAllowedPromos, builder.getPromos().size());
	}

	public PromotionBuilder getBuilder() {
		return builder;
	}

	public int getMaxAllowedPromos() {
		return maxAllowedPromos;
	}

	public List<IPromotionSvc> getAvailablePromos() {
		return this.builder.getPromos();
	}

	public Cart checkout(Cart cart) {
		int promotiosAppliedCnt = 0;
		if (promotiosAppliedCnt <= maxAllowedPromos) {
			for (IPromotionSvc eachPromo : getAvailablePromos()) {
				eachPromo.applyPromotion(cart);
				promotiosAppliedCnt ++;
			}
		}
		System.out.println("Total Price : "+cart.getCartTotalAfterPromotions());
		return cart;
	}

}
