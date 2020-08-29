package com.promotion.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;
import com.promotion.engine.svc.ComboItemFixedPricePromotionSvc;
import com.promotion.engine.svc.IPromotionSvc;
import com.promotion.engine.svc.NquantityFixedPricePromotionSvc;
import com.promotion.engine.svc.PromotionBuilder;
import com.promotion.engine.util.CartCheckOutSvc;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
public class PromotionEngineTest {
	

	private CartCheckOutSvc checkoutSvc;
	
	private Cart cart;
	
	private void shouldInitiateCartAndPromotions() {
		PromotionBuilder builder = buildPromotions();
		checkoutSvc = new CartCheckOutSvc(builder, 2);
	}
	
	@Test
	public void shouldProcessForScenarioA() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 5, 50));
		cart.addItemsToCart(new Item("B", 5, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		shouldInitiateCartAndPromotions();
		assertEquals(checkoutSvc.checkout(cart).getCartTotalAfterPromotions(), 370);
	}
	
	@Test
	public void shouldProcessForScenarioB() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 1, 50));
		cart.addItemsToCart(new Item("B", 1, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		shouldInitiateCartAndPromotions();
		assertEquals(checkoutSvc.checkout(cart).getCartTotalAfterPromotions(), 100);
	}
	
	
	@Test
	public void shouldProcessForScenarioC() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 3, 50));
		cart.addItemsToCart(new Item("B", 5, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		cart.addItemsToCart(new Item("D", 1, 20));
		shouldInitiateCartAndPromotions();
		assertEquals(checkoutSvc.checkout(cart).getCartTotalAfterPromotions(), 280);
	}
	
	private PromotionBuilder buildPromotions() {
		IPromotionSvc promo1 = new NquantityFixedPricePromotionSvc();
		IPromotionSvc promo2 = new ComboItemFixedPricePromotionSvc();
		PromotionBuilder builder = PromotionBuilder.builder().add(promo1).add(promo2).build();
		System.out.println("#######################################################");
		return builder;
	}

}
