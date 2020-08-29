package com.promotion.engine.svc;

import java.util.ArrayList;
import java.util.List;

public class PromotionBuilder {

	private List<IPromotionSvc> availablePromotions;
	private final IPromotionSvc defaultNoPromotionSvc = new NoPromotionSvc();

	public PromotionBuilder(Builder builder) {
		this.availablePromotions = builder.getPromos();
		/*Apart from the promotions which are added by admin, this default svc is required for price calculation*/
		this.availablePromotions.add(defaultNoPromotionSvc);
	}

	public static Builder builder() {
		return new PromotionBuilder.Builder();
	}

	public List<IPromotionSvc> getPromos() {
		List<IPromotionSvc> list = new ArrayList<>();
		list.addAll(availablePromotions);
		return list;
	}

	public static class Builder {
		final List<IPromotionSvc> availablePromotions = new ArrayList<>();

		public Builder add(IPromotionSvc promo) {
			this.availablePromotions.add(promo);
			return this;
		}

		public List<IPromotionSvc> getPromos() {
			return this.availablePromotions;
		}

		public PromotionBuilder build() {
			return new PromotionBuilder(this);
		}
	}

}
