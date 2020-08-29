package com.promotion.engine.svc;

import java.util.HashMap;
import java.util.Map;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

public class NquantityFixedPricePromotionSvc implements IPromotionSvc {

	private final static int PRIORITY = 0;

	private static Map<String, ItemPromo> itemToFixPrice = new HashMap<>();

	public NquantityFixedPricePromotionSvc() {
		initializeItemToFixPrice();
	}

	private static void initializeItemToFixPrice() {
		itemToFixPrice.put("A", new ItemPromo("A", 3, 130.0));
		itemToFixPrice.put("B", new ItemPromo("B", 2, 45));
		// Other promos can be added similarly
	}

	@Override
	public void applyPromotion(Cart cart) {
		for (String id : cart.getItems().keySet()) {
			Item item = cart.getItems().get(id);
			
			double totalPriceForCurrentItem = 0;
			if (!item.isPromotionApplied()) {
				/*If promotion is not applied on this item , 
				 * check if this item is eligible for this promotion type and 
				 * if it has minimum quantity for being eligible for this promotion*/
				if (itemToFixPrice.containsKey(id)
						&& item.getQuantity() >= itemToFixPrice.get(id).getMinQuantity()) {
					
					int promotionLotQuanity = item.getQuantity() / itemToFixPrice.get(id).getMinQuantity();
					totalPriceForCurrentItem += promotionLotQuanity * itemToFixPrice.get(id).getDiscountedPrice();
					cart.incrementCartPrice(totalPriceForCurrentItem);
					item.removeQuantityForWhichPromotionApplied(promotionLotQuanity * itemToFixPrice.get(id).getMinQuantity());
					updateItemAfterPromotion(cart, item);
				}
			}
			System.out.println("Promo-1 applied on item "+item.getId()+" with price " + totalPriceForCurrentItem);
		}

	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	private static class ItemPromo {
		private String itemId;
		private int minQuantity;
		private double discountedPrice;

		public ItemPromo(String itemId, int quantity, double discountedPrice) {
			super();
			this.itemId = itemId;
			this.minQuantity = quantity;
			this.discountedPrice = discountedPrice;
		}

		public String getItemId() {
			return itemId;
		}

		public int getMinQuantity() {
			return minQuantity;
		}

		public double getDiscountedPrice() {
			return discountedPrice;
		}

	}

}
