package edu.towson.cis.cosc603.project5.coffeemaker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

/**
 * Recipe object for the coffee maker
 * @author Josh
 * @version $Revision: 1.0 $
 */
public class Recipe implements Cloneable{
    private String name;
    private int price;
    private int amtCoffee;
    private int amtMilk;
    private int amtSugar;
    private int amtChocolate;
    
    public Recipe(){
    	
    }
    
    public Recipe(String name, int price, int amtCoffee,
    		int amtMilk, int amtSugar, int amtChocolate) {
    	this.name = name;
    	this.price = filterInput(price);
    	this.amtCoffee = filterInput(amtCoffee);
    	this.amtMilk = filterInput(amtMilk);
    	this.amtSugar = filterInput(amtSugar);
    	this.amtChocolate = filterInput(amtChocolate);    	
	}
    
    public int filterInput(int input){
    	if (input >= 0) {
    		return input;
		}
    	return 0;
    }
    
    /**
     * Method getAmtChocolate.
     * @return int
     */
    public int getAmtChocolate() {
        return amtChocolate;
    }
    /**
     * Method setAmtChocolate.
     * @param amtChocolate int
     */
   /* public void setAmtChocolate(int amtChocolate) {
    	this.amtChocolate = filterInput(amtChocolate);
    }
    /**
     * Method getAmtCoffee.
     * @return int
     */
    public int getAmtCoffee() {
        return amtCoffee;
    }
    /**
     * Method setAmtCoffee.
     * @param amtCoffee int
     */
 /*   public void setAmtCoffee(int amtCoffee) {
    	this.amtCoffee = filterInput(amtCoffee);
    }
    /**
     * Method getAmtMilk.
     * @return int
     */
    public int getAmtMilk() {
        return amtMilk;
    }
    /**
     * Method setAmtMilk.
     * @param amtMilk int
     */
   /* public void setAmtMilk(int amtMilk) {
    	this.amtMilk = filterInput(amtMilk);
    }
    /**
     * Method getAmtSugar.
     * @return int
     */
    public int getAmtSugar() {
        return amtSugar;
    }
    /**
     * Method setAmtSugar.
     * @param amtSugar int
     */
    public void setAmtSugar(int amtSugar) {
    	this.amtSugar = filterInput(amtSugar);
    }
    /**
     * Method getName.
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * Method setName.
     * @param name String
     */
  /*  public void setName(String name) {
        this.name = name;
    }
    /**
     * Method getPrice.
     * @return int
     */
    public int getPrice() {
        return price;
    }
    /**
     * Method setPrice.
     * @param price int
     */
  /*  public void setPrice(int price) {
    	this.price = filterInput(price);
    } 
    /**
     * Method equals.
     * @param r Recipe
     * @return boolean
     */
    public boolean equals(Object r) {
    	if (this == r) {
    		return true;
		}
    	if (r instanceof Recipe) {
    		final Recipe rep = (Recipe) r;
    		
    		if (rep.getName() == null 
    				|| this.name == null) {
				return false;
			}
    		if ((this.name).equals(rep.getName())) {
    			return true;
			}    		
		}
    	return false;       
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + this.price;
        hash = 97 * hash + this.amtCoffee;
        hash = 97 * hash + this.amtMilk;
        hash = 97 * hash + this.amtSugar;
        hash = 97 * hash + this.amtChocolate;
    	return hash;       
    }
    
    /**
     * Method toString.
     * @return String
     */
    public String toString() {
    	return name;
    }
    
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
	}
    
    public final  Recipe deepCopy(){
    	final Recipe rep = new Recipe(name, price, amtCoffee, 
    			amtMilk, amtSugar, amtChocolate);
    	return rep;
    }
    
    protected Object clone() throws CloneNotSupportedException {
    	 
    	final Recipe clone=(Recipe)super.clone();
     
        return clone;
        
    }
    
}
