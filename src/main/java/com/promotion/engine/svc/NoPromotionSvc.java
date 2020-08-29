package com.promotion.engine.svc;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

/*
 * This Svc does flat price calculation on the item quantities which are left over after all promotions are applied.
 * This is a default promotion svc which will be applied.
 */
public class NoPromotionSvc implements IPromotionSvc {

	private final static int PRIORITY = 3;


	@Override
	public void applyPromotion(Cart cart) {
		for (String id : cart.getItems().keySet()) {
			Item item = cart.getItems().get(id);
			double totalPriceForCurrentItem = 0;
			totalPriceForCurrentItem += item.getQuantity() * item.getUnitPrice();
			cart.setCartTotalAfterPromotions(totalPriceForCurrentItem);
			item.removeQuantityForWhichPromotionApplied(item.getQuantity());
			updateItemAfterPromotion(cart, item);
			cart.addItemsToCart(item);
		}

	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}
}
