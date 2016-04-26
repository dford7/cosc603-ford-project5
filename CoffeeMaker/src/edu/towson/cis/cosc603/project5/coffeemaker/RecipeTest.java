package edu.towson.cis.cosc603.project5.coffeemaker;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecipeTest {
	private Recipe rep;
	@Before
	public void setUp() throws Exception {
		rep = new Recipe("Coffee", 50, 6, 1, 1, 0);
	}

	@After
	public void tearDown() throws Exception {
		rep = null;
	}

	@Test
	public void testFilterInput1() {
		assertEquals(0, rep.filterInput(-2));
	}
	
	@Test
	public void testFilterInput2() {
		assertEquals(2, rep.filterInput(2));
	}
	
	@Test
	public void testGetAmtChocolate() {
		assertEquals(0, rep.getAmtChocolate());
	}

	@Test
	public void testGetAmtCoffee() {
		assertEquals(6, rep.getAmtCoffee());
	}
	@Test
	public void testGetAmtMilk() {
		assertEquals(1, rep.getAmtMilk());
	}
	@Test
	public void testGetAmtSugar() {
		assertEquals(1, rep.getAmtSugar());
	}
	@Test
	public void testSetAmtSugar1() {
		rep.setAmtSugar(5);
		assertEquals(5, rep.getAmtSugar());
	}
	@Test
	public void testSetAmtSugar2() {
		rep.setAmtSugar(-5);
		assertEquals(0, rep.getAmtSugar());
	}
	@Test
	public void testGetName() {
		assertTrue(rep.getName().equals("Coffee"));		
	}
	@Test
	public void testGetPrice() {
		assertEquals(50, rep.getPrice());
	}
	@Test
	public void testDeepCopy() {
		assertTrue(rep.equals(rep.deepCopy()));
	}
	
	@Test
	public void testEquals1() {
		assertTrue(rep.equals(rep));
	}
	
	@Test
	public void testEquals2() {
		assertEquals(false, rep.equals(null));
		
	}
	
	@Test
	public void testToString(){
		assertTrue(rep.toString().equals("Coffee"));		
	}
	
	@Test
	public void testHashCode(){
		assertEquals(rep.hashCode(),rep.hashCode());
	}
	
}
