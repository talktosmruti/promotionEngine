package com.promotion.engine.svc;

import java.util.HashMap;
import java.util.Map;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

/*
 * This is promo 1 as per question where it will be 
 * applied on n-items with fixed price.
 */
public class NquantityFixedPricePromotionSvc implements IPromotionSvc {

	private final static int PRIORITY = 0;

	/*Map of item vs promo details*/
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
				/*If promotion is not already not applied on this item , 
				 * check if this item is eligible for this promotion type and 
				 * if it has minimum quantity for being eligible for this promotion*/
				if (itemToFixPrice.containsKey(id)
						&& item.getQuantity() >= itemToFixPrice.get(id).getMinQuantity()) {
					
					/*Lot quantity on which promotion will be applied.
					 * E.g if there are 7 A's, then there are 7/3 = 2 lots*/
					int promotionLotQuanity = item.getQuantity() / itemToFixPrice.get(id).getMinQuantity();
					totalPriceForCurrentItem += promotionLotQuanity * itemToFixPrice.get(id).getDiscountedPrice();
					/*Total calculated price for the eligible item quantities*/
					cart.incrementCartPrice(totalPriceForCurrentItem);
					/*Item quantities on which promotion is applied is removed, 
					 * so it wont be considered for further promo types*/
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

	/*
	 * This is each item type on which promo can be applied.
	 * 
	 */
	private static class ItemPromo {
		private String itemId;
		/*Min quantity on which promo can be applied*/
		private int minQuantity;
		/*price after promo applied.*/
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
