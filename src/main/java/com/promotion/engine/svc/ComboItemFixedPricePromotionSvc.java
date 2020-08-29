package com.promotion.engine.svc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;

public class ComboItemFixedPricePromotionSvc implements IPromotionSvc {

	private final static int PRIORITY = 1;

	private List<ItemCombos> itemCombos = new ArrayList<>();

	public ComboItemFixedPricePromotionSvc() {
		initializeComboPromos();
	}

	private void initializeComboPromos() {
		ItemCombos combo1 = new ItemCombos(Arrays.asList("C", "D"), 30.0);
		itemCombos.add(combo1);

	}

	@Override
	public void applyPromotion(Cart cart) {
		for (ItemCombos eachItemCombo : itemCombos) {
			boolean isAllRequiredItemExists = true;
			int maxCombosCanBeFormed = Integer.MAX_VALUE;
			/*
			 * If we have 4 C and 3 D, this maxCombosCanBeFormed = min(4, 3) = 3. So
			 * discounted price 3*30 = 90 + price of 1 unit of C
			 */
			/*
			 * For each type of combo promos, first its checked if all required item exists
			 * in cart and if promo is not applied already.
			 */

			for (String reqdItemId : eachItemCombo.getItemCombos()) {
				if (!cart.getItems().containsKey(reqdItemId) || cart.getItems().get(reqdItemId).isPromotionApplied()) {
					isAllRequiredItemExists = false;
					break;
				} else {
					maxCombosCanBeFormed = Math.min(maxCombosCanBeFormed,
							cart.getItems().get(reqdItemId).getQuantity());
				}
			}
			if (isAllRequiredItemExists) {
				/* combo discount price added in cart level */
				System.out.println("Promo 2 applied on item combo : " + eachItemCombo.getItemCombos()
						+ " total discounted price added " + maxCombosCanBeFormed * eachItemCombo.getDiscountedPrice());
				cart.incrementCartPrice(maxCombosCanBeFormed * eachItemCombo.getDiscountedPrice());
				/*
				 * Now reduce each item quantity by number of minComobs.So it will deduct the
				 * item quantity for which promotion is applied
				 */
				for (String reqdItemId : eachItemCombo.getItemCombos()) {
					Item item = cart.getItems().get(reqdItemId);
					item.removeQuantityForWhichPromotionApplied(maxCombosCanBeFormed);
					updateItemAfterPromotion(cart, item);

				}
			}
		}
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	private static class ItemCombos {
		private List<String> itemCombos;
		private double discountedPrice;

		public ItemCombos(List<String> itemCombos, double discountedPrice) {
			super();
			this.itemCombos = itemCombos;
			this.discountedPrice = discountedPrice;
		}

		public List<String> getItemCombos() {
			return itemCombos;
		}

		public double getDiscountedPrice() {
			return discountedPrice;
		}
	}

}
