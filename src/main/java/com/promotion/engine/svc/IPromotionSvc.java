package com.promotion.engine.svc;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

public interface IPromotionSvc {
	public void applyPromotion(Cart cart);
	public int getPriority();
	
	default void updateItemAfterPromotion(Cart cart, Item item) {
		item.setPromotionApplied(true);
		cart.addItemsToCart(item);
	}
}
