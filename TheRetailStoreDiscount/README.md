#  TheRetailStoreDiscount

Use Cases:

1. If the user is an employee of the store, he gets a 30% discount 
2. If the user is an affiliate of the store, he gets a 10% discount 
3. If the user has been a customer for over 2 years, he gets a 5% discount. 
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 
as a discount). 
5. The percentage based discounts do not apply on groceries. 6. A user can get only one of the percentage based discounts on a bill.

Methodology
------------
The main method of the Program is MainStore where you run the test execution.
The packages are broken down to bean,configuration,inventory,services and test

The Customer
------------
There are 3 Customer types: Retail Customer, Employee and Affiliate.
Customer class is abstract where subclasses are termed Employee, RetailCustomer and Affiliate.
The discount type for customer is stored in enum  Discounts. The discount method (Percentage/Amount)
is stored in DiscountMethod enum class along with the discount values.
This is a subtype and its stored as instance fields in the Discounts enum. 

The Cart Items
-------------
The  cart items are stored in CartItems.class where it extends the RetailItems. RetailItems is the super class.
So if any other class can extend RetailItems in future. The CartItems class will add the
inventory items taken from CartInventory and you can add the quantity. quantity is a double param since you can add any values like gram wise.


The Inventory
-------------
The Inventory list is saved in CartItems enum class. This contains the all the items.
You specify the item and if the if the item is grocery, pharmacy, etc. The cart item Inventory type (grocery, pharmacy etc.,)
is stored in BaseInventoryTypes enum. CartInventory enum implements BaseInventoryItemsList. As if you want any other inventory, you can simply implement this.

Discount OmissionList
---------------------
You can declare an omission list on an interface DiscountExclusionList prior to starting the program.

The Calculation Service
------------------------
The CalculateCheckoutPrice class is a final class where the calculation methods are in static.
 It calculates all the discounts and the totals of the cart Items.


How to run the program?
----------------------
In the MainStore method, simply create a customer type. 
Add the shopping list -List<RetailItems> shoppingCart.
Then the calculate methods are in CalculateCheckoutPrice where the discount logic and totals are defined.

	Customer customer= new Employee();
		customer.setUserRelationshipDays(800);		
		
		List<RetailItems> shoppingCart = new ArrayList<>();
		shoppingCart.add(new CartItems(CartInventory.BEETROOT,100));
		shoppingCart.add(new CartItems(CartInventory.DETTOL,50));
		shoppingCart.add(new CartItems(CartInventory.LEEKS,50));
		
		TotalPriceDetails totalPrice=CalculateCheckoutPrice.calculateTotalPrice(shoppingCart, customer);
		System.out.println(totalPrice);
		
		
Code Coverage: Run code coverage on TestDiscountSuite.