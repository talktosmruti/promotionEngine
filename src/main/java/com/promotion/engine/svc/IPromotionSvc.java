package com.promotion.engine.svc;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

public interface IPromotionSvc {
	
	/*Apply this type of promotion on eligible cart items.*/
	public void applyPromotion(Cart cart);
	
	/*Get the priority of the promotion to decide the order for applying this type.*/
	public int getPriority();
	
	/*Default implementation for updating the item and cart once promtion is applied.*/
	default void updateItemAfterPromotion(Cart cart, Item item) {
		item.setPromotionApplied(true);
		cart.addItemsToCart(item);
	}
}
