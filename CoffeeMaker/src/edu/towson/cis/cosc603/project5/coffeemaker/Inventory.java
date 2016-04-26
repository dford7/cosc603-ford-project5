package edu.towson.cis.cosc603.project5.coffeemaker;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Inventory for the coffee maker
 * @author Josh
 * @version $Revision: 1.0 $
 */
public class Inventory implements Cloneable{
    
    private  int coffee;
    private  int milk;
    private  int sugar;
    private  int chocolate;
    
    public Inventory() {
    	setCoffee(15);
    	setMilk(15);
    	setSugar(15);
    	setChocolate(15);
    	if (this.coffee != 15) {    		
    		throw new RuntimeException();    		
		}
    	if (this.milk != 15) {    		
    		throw new RuntimeException();    		
		}
    	if (this.sugar != 15) {    		
    		throw new RuntimeException();    		
		}
    	if (this.chocolate != 15) {    		
    		throw new RuntimeException();    		
		}
    }
    
    public Inventory(int coffee, int milk, int sugar, int chocolate) {
    	setCoffee(coffee);
    	setMilk(milk);
    	setSugar(sugar);
    	setChocolate(chocolate);
    	if (this.coffee != coffee) {    		
    		throw new RuntimeException();    		
		}
    	if (this.milk != milk) {    		
    		throw new RuntimeException();    		
		}
    	if (this.sugar != sugar) {    		
    		throw new RuntimeException();    		
		}
    	if (this.chocolate != chocolate) {    		
    		throw new RuntimeException();    		
		}
    }
    
    /**
     * Method getChocolate.
     * @return int
     */
    public int getChocolate() {
        return chocolate;
    }
    /**
     * Method setChocolate.
     * @param chocolate int
     */
    public void setChocolate(int chocolate) {
    	if(chocolate > 0) {
    		this.chocolate = chocolate;
    	}
    	else {
    		this.chocolate = 0;
    	}
        
    }
    /**
     * Method getCoffee.
     * @return int
     */
    public int getCoffee() {
        return coffee;
    }
    /**
     * Method setCoffee.
     * @param coffee int
     */
    public void setCoffee(int coffee) {
    	if(coffee > 0) {
    		this.coffee = coffee;
    	}
    	else {
    		this.coffee = 0;
    	}
    }
    /**
     * Method getMilk.
     * @return int
     */
    public int getMilk() {
        return milk;
    }
    /**
     * Method setMilk.
     * @param milk int
     */
    public void setMilk(int milk) {
    	if(milk > 0) {
    		this.milk = milk;
    	}
    	else {
    		this.milk = 0;
    	}
    }
    /**
     * Method getSugar.
     * @return int
     */
    public int getSugar() {
        return sugar;
    }
    /**
     * Method setSugar.
     * @param sugar int
     */
    public void setSugar(int sugar) {
    	if(sugar > 0) {
    		this.sugar = sugar;
    	}
    	else {
    		this.sugar = 0;
    	}
    }
    
    /**
     * Returns true if there are enough ingredients to make
     * the beverage.
     * @param r
    
     * @return boolean */
    public boolean enoughIngredients(Recipe r) {
        boolean isEnough = true;
        if(this.coffee < r.getAmtCoffee()) {
            isEnough = false;
        }
        if(this.milk < r.getAmtMilk()) {
            isEnough = false;
        }
        if(this.sugar < r.getAmtSugar()) {
            isEnough = false;
        }
        if(this.chocolate < r.getAmtChocolate()) {
            isEnough = false;
        }
        return isEnough;
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString() {
    	return "Coffee: " + getCoffee() + System.getProperty("line.separator")  +
			"Milk: " + getMilk() + System.getProperty("line.separator")  +
			"Sugar: " + getSugar() + System.getProperty("line.separator")  +
			"Chocolate: " + getChocolate() + System.getProperty("line.separator") ;
    }
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException{
      //  stream.defaultReadObject();
	}
    
    public Inventory deepCopy(){    	
		return new Inventory(coffee, milk, sugar, chocolate);    	
    }
    protected Object clone() throws CloneNotSupportedException {
   	 
        final Inventory clone=(Inventory)super.clone();
     
        return clone;
        
    }
}
