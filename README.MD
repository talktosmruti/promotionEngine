Envrioment requirement:
- Java 8
- Maven

How to Run:
- clone the repo
- build using mvn clean install
- Test cases written in test folder com.promotion.engine.PromotionEngineTest which contains 3 test scenarios in question and 1 more custom scenario.
  These can be run and also few sysouts/ logs added for observation.


LLD:
Model classes : 
  Item (id, quantity, unitPrice, isPromotionApplied) |
  Cart (Map<String, Item> items, totalCartPrice)
  
Service classes :
  interface IPromotionSvc:
	     void applyPromotion(Cart cart);
	     int getPriority(); |
    Implementation NquantityFixedPricePromotionSvc (for promo1 mentioned in question) |
    Implementation ComboItemFixedPricePromotionSvc (for promo2 mentioned in question) |  
    Implementation NoPromotionSvc(This is a default class which will be added to calculate the prices of items on which promotions not applied) |
  
  PromotionBuilder: (This is a builder class to initialize application with dynamic number of promotions.) |
  CartCheckOutSvc:(This svc initilized with all the promotions, it takes the cart and calculates the total price.) |

Util class:
  PromotionsUtil: (Contains only 1 method to sort the promotions as per their priorities) |
  

  
