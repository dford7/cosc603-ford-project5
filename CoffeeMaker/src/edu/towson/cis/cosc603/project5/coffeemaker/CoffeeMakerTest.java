package edu.towson.cis.cosc603.project5.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase implements Cloneable{
	private CoffeeMaker cm;
	private Inventory iv;
	private Recipe r1;

	public void setUp() {
		cm = new CoffeeMaker();
		iv = cm.checkInventory();

		r1 = new Recipe("Coffee", 50, 6, 1, 1, 0);
	/*	r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);*/
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	public void testAddInventory1(){
		
		assertTrue(cm.addInventory(iv.getCoffee(), iv.getMilk(), iv.getSugar()
				, iv.getChocolate()));
			
	}
	
	public void testCheckInventory(){
		Inventory t = cm.checkInventory();
		assertTrue(t != null);
		assertEquals(t.getChocolate(), 15);		
		assertEquals(t.getCoffee(), 15);
		assertEquals(t.getMilk(), 15);
		assertEquals(t.getSugar(), 15);	
	}
	
	public void testPurchaseBeverage1(){
		cm.addRecipe(r1);
		assertEquals(10,cm.makeCoffee(r1, 60));
	}
		
}