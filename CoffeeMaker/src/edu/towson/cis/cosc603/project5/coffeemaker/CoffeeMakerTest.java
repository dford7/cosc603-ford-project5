package edu.towson.cis.cosc603.project5.coffeemaker;

import org.junit.Test;

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

	@Test
	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}

	@Test
	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	@Test
	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	@Test
	public void testAddInventory2(){
		
		assertFalse(cm.addInventory(iv.getCoffee(), -5, iv.getSugar()
				, iv.getChocolate()));
			
	}
	
	@Test
	public void testAddInventory1(){
		
		assertTrue(cm.addInventory(iv.getCoffee(), iv.getMilk(), iv.getSugar()
				, iv.getChocolate()));
			
	}
	
	@Test
	public void testCheckInventory(){
		Inventory t = cm.checkInventory();
		assertTrue(t != null);
		assertEquals(t.getChocolate(), 15);		
		assertEquals(t.getCoffee(), 15);
		assertEquals(t.getMilk(), 15);
		assertEquals(t.getSugar(), 15);	
	}
	
	@Test
	public void testPurchaseBeverage1(){
		cm.addRecipe(r1);
		assertEquals(10,cm.makeCoffee(r1, 60));
	}
		
	@Test
	public void testisRecipeExisting(){
		Recipe r2 = new Recipe("Lemon juice", 50, 6, 1, 1, 0);
		cm.addRecipe(r2);
		assertFalse(cm.addRecipe(r2));		
	}
	
	@Test
	public void testMakeCoffee1(){
		Recipe r2 = new Recipe("Coffee", 50, 600, 100, 100, 300);
		cm.addRecipe(r2);
		assertEquals(50, cm.makeCoffee(r2, 50));		
	}
	
	@Test
	public void testMakeCoffee2(){
		cm.addRecipe(r1);
		assertEquals(5, cm.makeCoffee(r1, 5));		
	}
	
	@Test
	public void testGetRecipes(){
		try {
			Recipe [] rep = cm.getRecipes();
			assertEquals(4, rep.length);
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetRecipeForName(){
		cm.addRecipe(r1);
		Recipe t = cm.getRecipeForName(r1.getName());
		assertTrue(t.getName().equals(r1.getName()));		
	}
	
	@Test
	public void testClone(){
		cm.addRecipe(r1);
		try {
			CoffeeMaker c = (CoffeeMaker)cm.clone();
			assertTrue(c.getRecipeForName(r1.getName()).equals(cm.getRecipeForName(r1.getName())));
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}