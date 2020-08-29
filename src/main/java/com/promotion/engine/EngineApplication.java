package com.promotion.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;
import com.promotion.engine.svc.ComboItemFixedPricePromotionSvc;
import com.promotion.engine.svc.IPromotionSvc;
import com.promotion.engine.svc.NquantityFixedPricePromotionSvc;
import com.promotion.engine.svc.PromotionBuilder;
import com.promotion.engine.util.CartCheckOutSvc;

@SpringBootApplication
public class EngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineApplication.class, args);
		test();
		
	}
	
	public static void test() {
		Cart cart = new Cart();
		cart.addItemsToCart(new Item("A", 5, 50));
		cart.addItemsToCart(new Item("B", 5, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		//cart.addItemsToCart(new Item("D", 0, 15));
		IPromotionSvc promo1 = new NquantityFixedPricePromotionSvc();
		IPromotionSvc promo2 = new ComboItemFixedPricePromotionSvc();
		PromotionBuilder promo = PromotionBuilder.builder().add(promo1).add(promo2).build();
		CartCheckOutSvc checkoutSvc = new CartCheckOutSvc(promo, 2);
		checkoutSvc.checkout(cart);
		
	}

}
