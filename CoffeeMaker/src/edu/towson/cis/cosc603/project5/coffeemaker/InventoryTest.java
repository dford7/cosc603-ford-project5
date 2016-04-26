package edu.towson.cis.cosc603.project5.coffeemaker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {
	Inventory iv;

	@Before
	public void setUp() throws Exception {
		iv = new Inventory();
	}

	@After
	public void tearDown() throws Exception {
		iv = null;
	}

	@Test
	public void testGetChocolate() {
		assertEquals(15, iv.getChocolate());		
	}
	
	@Test
	public void testSetChocolate1() {
		iv.setChocolate(100);
		assertEquals(100, iv.getChocolate());		
	}
	
	@Test
	public void testSetChocolate2() {		
		iv.setChocolate(-100);
		assertEquals(0, iv.getChocolate());
	}
	
	@Test
	public void testGetCoffee() {
		assertEquals(15, iv.getCoffee());		
	}
	
	@Test
	public void testSetCoffee1() {
		iv.setCoffee(100);
		assertEquals(100, iv.getCoffee());		
	}
	
	@Test
	public void testSetCoffee2() {		
		iv.setCoffee(-100);
		assertEquals(0, iv.getCoffee());
	}
	@Test
	public void testGetMilk() {
		assertEquals(15, iv.getMilk());		
	}
	
	@Test
	public void testSetMilk1() {
		iv.setMilk(100);
		assertEquals(100, iv.getMilk());		
	}
	
	@Test
	public void testSetMilk2() {		
		iv.setMilk(-100);
		assertEquals(0, iv.getMilk());
	}
	
	@Test
	public void testGetSugar() {
		assertEquals(15, iv.getSugar());		
	}
	
	@Test
	public void testSetSugar1() {
		iv.setSugar(100);
		assertEquals(100, iv.getSugar());		
	}
	
	@Test
	public void testSetSugar2() {		
		iv.setSugar(-100);
		assertEquals(0, iv.getSugar());
	}
	
	@Test
	public void testEnoughIngredients1() {
		Recipe r = new Recipe("Coffe", 50, 10, 5, 5, 6);
		assertEquals(true, iv.enoughIngredients(r) );
	}
	
	@Test
	public void testEnoughIngredients2() {
		Recipe r = new Recipe("Coffe", 50, 100, 5, 5, 6);
		assertEquals(false, iv.enoughIngredients(r) );
	}
	
	@Test
	public void testEnoughIngredients3() {
		Recipe r = new Recipe("Coffe", 50, 10, 50, 5, 6);
		assertEquals(false, iv.enoughIngredients(r) );
	}
	
	@Test
	public void testEnoughIngredients4() {
		Recipe r = new Recipe("Coffe", 50, 10, 5, 52, 6);
		assertEquals(false, iv.enoughIngredients(r) );
	}
	
	@Test
	public void testEnoughIngredients5() {
		Recipe r = new Recipe("Coffe", 50, 10, 5, 5, 96);
		assertEquals(false, iv.enoughIngredients(r) );
	}
	
	@Test
	public void testToString() {
		String s = "Coffee: " + iv.getCoffee() + System.getProperty("line.separator")  +
				"Milk: " + iv.getMilk() + System.getProperty("line.separator")  +
				"Sugar: " + iv.getSugar() + System.getProperty("line.separator")  +
				"Chocolate: " + iv.getChocolate() + System.getProperty("line.separator");
		assertTrue(s.equals(iv.toString()));
				
	}
	
	@Test
	public void testDeepCopy() {
		Inventory t = iv.deepCopy();
		assertEquals(t.getChocolate(), iv.getChocolate());
		assertEquals(t.getCoffee(), iv.getCoffee());
		assertEquals(t.getMilk(), iv.getMilk());
		assertEquals(t.getSugar(), iv.getSugar());
	}
	
	@Test
	public void testClone(){
		Inventory t;
		try {
			t = (Inventory)iv.clone();
			assertEquals(t.getChocolate(), iv.getChocolate());
			assertEquals(t.getCoffee(), iv.getCoffee());
			assertEquals(t.getMilk(), iv.getMilk());
			assertEquals(t.getSugar(), iv.getSugar());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
	}
	
	
}
