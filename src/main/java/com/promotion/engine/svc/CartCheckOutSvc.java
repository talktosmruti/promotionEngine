package com.promotion.engine.svc;

import java.util.List;

import com.promotion.engine.model.Cart;
import com.promotion.engine.svc.IPromotionSvc;
import com.promotion.engine.svc.PromotionBuilder;

public class CartCheckOutSvc {

	/*All the required promotions can be added to this builder.*/
	private PromotionBuilder builder;
	
	/*To keep a control on number of promotions that can be applied*/
	private int maxAllowedPromos;

	/*Takes promotion builder which can contain different type of promotion*/
	public CartCheckOutSvc(PromotionBuilder builder, int maxAllowedPromos) {
		this.builder = builder;
		/*Max possible promos is either configured limit or total promos available.*/
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

	/*
	 * Updates the cart with total price.
	 */
	public void checkout(Cart cart) {
		int promotiosAppliedCnt = 0;
		if (promotiosAppliedCnt <= maxAllowedPromos) {
			/*Each promos will be applied on order of their priority*/
			for (IPromotionSvc eachPromo : getAvailablePromos()) {
				eachPromo.applyPromotion(cart);
				promotiosAppliedCnt ++;
			}
		}
		System.out.println("Total Price : "+cart.getCartTotalAfterPromotions());
		System.out.println("-------------------------------");
	}

}
