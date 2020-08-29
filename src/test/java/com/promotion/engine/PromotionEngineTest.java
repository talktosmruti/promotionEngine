package com.promotion.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.promotion.engine.model.Cart;
import com.promotion.engine.model.Item;
import com.promotion.engine.svc.CartCheckOutSvc;
import com.promotion.engine.svc.ComboItemFixedPricePromotionSvc;
import com.promotion.engine.svc.IPromotionSvc;
import com.promotion.engine.svc.NquantityFixedPricePromotionSvc;
import com.promotion.engine.svc.PromotionBuilder;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
public class PromotionEngineTest {
	

	private CartCheckOutSvc checkoutSvc;
	
	private Cart cart;
	
	private void shouldInitiateAvailablePromotions() {
		PromotionBuilder builder = buildPromotions();
		checkoutSvc = new CartCheckOutSvc(builder, 2);
	}
	
	@Test
	public void shouldProcessForScenarioA() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 5, 50));
		cart.addItemsToCart(new Item("B", 5, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		shouldInitiateAvailablePromotions();
		checkoutSvc.checkout(cart);
		assertEquals(cart.getCartTotalAfterPromotions(), 370);
	}
	
	@Test
	public void shouldProcessForScenarioB() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 1, 50));
		cart.addItemsToCart(new Item("B", 1, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		shouldInitiateAvailablePromotions();
		checkoutSvc.checkout(cart);
		assertEquals(cart.getCartTotalAfterPromotions(), 100);
	}
	
	
	@Test
	public void shouldProcessForScenarioC() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 3, 50));
		cart.addItemsToCart(new Item("B", 5, 30));
		cart.addItemsToCart(new Item("C", 1, 20));
		cart.addItemsToCart(new Item("D", 1, 15));
		shouldInitiateAvailablePromotions();
		checkoutSvc.checkout(cart);
		assertEquals(cart.getCartTotalAfterPromotions(), 280);
	}
	
	@Test
	public void shouldProcessForScenarioD() {

	    cart = new Cart();
		cart.addItemsToCart(new Item("A", 10, 50));
		cart.addItemsToCart(new Item("B", 6, 30));
		cart.addItemsToCart(new Item("C", 5, 20));
		cart.addItemsToCart(new Item("D", 8, 15));
		/*Expected A - 3*promo1 = 3*130 + 1* 50 = 440
		 * Expected B- 3*promo1 = 3*45 = 135
		 * Expected C-D Combo - 5*promo2 = 5*30 = 150
		 * Rest 3 units of D - 3*15 = 45
		 * Total = 775
		 * */
		shouldInitiateAvailablePromotions();
		checkoutSvc.checkout(cart);
		assertEquals(cart.getCartTotalAfterPromotions(), 770);
	}
	
	private PromotionBuilder buildPromotions() {
		IPromotionSvc promo1 = new NquantityFixedPricePromotionSvc();
		IPromotionSvc promo2 = new ComboItemFixedPricePromotionSvc();
		/*Here 2 promos added, design is flexible to 
		 * add more in future through this chaining function add()*/
		PromotionBuilder builder = PromotionBuilder
										.builder()
										.add(promo1)
										.add(promo2)
										.build();
		System.out.println("#######################################################");
		return builder;
	}

}
