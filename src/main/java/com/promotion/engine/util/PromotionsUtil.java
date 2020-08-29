package com.promotion.engine.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.promotion.engine.svc.IPromotionSvc;

public class PromotionsUtil {

	/*
	 * Sort promotions as per priority.
	 */
	public static List<IPromotionSvc> sort(List<IPromotionSvc> availablePromotions) {
		return availablePromotions
				.stream()
				.sorted(Comparator.comparingInt(IPromotionSvc::getPriority))
				.collect(Collectors.toList());
	}

}
