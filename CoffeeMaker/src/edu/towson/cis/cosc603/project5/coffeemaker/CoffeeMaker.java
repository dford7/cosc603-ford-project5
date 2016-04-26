package edu.towson.cis.cosc603.project5.coffeemaker;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * CoffeeMaker object
 * @version $Revision: 1.0 $
 */
public class CoffeeMaker implements Cloneable{
	/** Array of recipes in coffee maker */
	private Recipe [] recipeArray;
	/** Number of recipes in coffee maker */
	private static final int NUM_RECIPES = 4;
	/** Array describing if the array is full */
	private final boolean [] recipeFull;
	/** Inventory of the coffee maker */
    private Inventory inventory;
	
    /**
     * Constructor for the coffee maker
     *
     */
	public CoffeeMaker() {
	    recipeArray = new Recipe[NUM_RECIPES];
	    recipeFull = new boolean[NUM_RECIPES];
		for(int i = 0; i < NUM_RECIPES; i++) {
		   recipeArray[i] = new Recipe();
		   recipeFull[i] = false;
		}
		inventory = new Inventory();
	}

	/**
	 * Returns true if a recipe is successfully added to the 
	 * coffee maker
	 * @param r
	
	 * @return boolean */
	public boolean addRecipe(Recipe r) {
        boolean canAddRecipe = !(isRecipeExisting(r));
        
        //Check for an empty recipe, add recipe to first empty spot
        if(canAddRecipe) {
        	final int emptySpot = indexOfEmptyRecipe();
        	if(emptySpot != -1) {
        		recipeArray[emptySpot] = r;
        		recipeFull[emptySpot] = true;
        	}
        	else {
        		canAddRecipe = false;
        	}
        }
        return canAddRecipe;
    }

	/**
	 * @return
	 */
	private int indexOfEmptyRecipe() {
		//int emptySpot = -1;
		int i = 0;
		while (i < NUM_RECIPES) {
			if(!recipeFull[i]) {
	               return i;
            }
			i++;
		}  
		return -1;
	}

	/**
	 * @param r
	 * @param canAddRecipe
	 * @return
	 */
	private boolean isRecipeExisting(Recipe r) {
		// boolean canAddRecipe = true;
		//Check if the recipe already exists
		int i = 0;
		while (i < NUM_RECIPES) {
			if(r.equals(recipeArray[i])) {
	               return true;
            }
			i++;
		}     
		return false;
	}
    
	/**
	 * Returns true if the recipe was deleted from the 
	 * coffee maker
	 * @param r
	
	 * @return boolean */
    public boolean deleteRecipe(Recipe r) {
      //  boolean canDeleteRecipe = false;
        if(r != null) {
        	int i = 0;
    		while (i < NUM_RECIPES) {
    			if(r.equals(recipeArray[i])) {
    	               return true;
                }
    			i++;
    		}         		
        }
      // return canDeleteRecipe;
        return false;
    }
    
    /**
     * Returns true if the recipe is successfully edited
     * @param oldRecipe
     * @param newRecipe
    
     * @return boolean */
    public boolean editRecipe(Recipe oldRecipe, Recipe newRecipe) {
        boolean canEditRecipe = false;
        for(int i = 0; i < NUM_RECIPES; i++) {
        	if(recipeArray[i].getName() != null && 
        			newRecipe.equals(recipeArray[i]) ) {	           
            	recipeArray[i] = new Recipe();            	
            	canEditRecipe = addRecipe(newRecipe);   
            	canEditRecipe = true;
        	}
        }
        return canEditRecipe;
    }
    
    /**
     * Returns true if inventory was successfully added
     * @param amtCoffee
     * @param amtMilk
     * @param amtSugar
     * @param amtChocolate
    
     * @return boolean */
    public boolean addInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
        boolean canAddInventory = true;
        if(isValidInventory(amtCoffee, amtMilk, amtSugar, amtChocolate)) { 
            canAddInventory = false;
        }
        else {     
        	int tmp =  inventory.getCoffee();
	        inventory.setCoffee(inventory.getCoffee() + amtCoffee);
	        tmp += amtCoffee;
	        if (tmp != inventory.getCoffee()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getMilk();
	        inventory.setMilk(inventory.getMilk() + amtMilk);
	        tmp += amtMilk;
	        if (tmp != inventory.getMilk()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getSugar();
	        inventory.setSugar(inventory.getSugar() + amtSugar);
	        tmp += amtSugar;
	        if (tmp != inventory.getSugar()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getChocolate();
	        inventory.setChocolate(inventory.getChocolate() + amtChocolate);
	        tmp += amtChocolate;
	        if (tmp != inventory.getChocolate()) {
	        	throw new RuntimeException();
			}
        }
        return canAddInventory;
    }

	/**
	 * @param amtCoffee
	 * @param amtMilk
	 * @param amtSugar
	 * @param amtChocolate
	 * @return
	 */
	private boolean isValidInventory(int amtCoffee, int amtMilk, int amtSugar, int amtChocolate) {
		return amtCoffee < 0 || amtMilk < 0 || 
        		amtSugar <  0 || amtChocolate < 0;
	}
    
    /**
     * Returns the inventory of the coffee maker
    
     * @return Inventory */
    public final Inventory checkInventory() {
    	final Inventory iv = new Inventory(inventory.getCoffee(), inventory.getMilk(),
    			inventory.getSugar(), inventory.getChocolate()) ;
        return iv;
    }
    
    /**
     * Returns the change of a user's beverage purchase, or
     * the user's money if the beverage cannot be made
     * @param r
     * @param amtPaid
    
     * @return int */
    public int makeCoffee(Recipe r, int amtPaid) {
        boolean canMakeCoffee = true;
        if(amtPaid < r.getPrice()) {
            canMakeCoffee = false;
        }
        if(!inventory.enoughIngredients(r)) {
            canMakeCoffee = false;
        }
        if(canMakeCoffee) {
        	int tmp = inventory.getCoffee();
	        inventory.setCoffee(inventory.getCoffee() - r.getAmtCoffee());
	        tmp -= r.getAmtCoffee();
	        if (tmp != inventory.getCoffee()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getMilk();
	        inventory.setMilk(inventory.getMilk() - r.getAmtMilk());
	        tmp -= r.getAmtMilk();
	        if (tmp != inventory.getMilk()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getSugar();
	        inventory.setSugar(inventory.getSugar() - r.getAmtSugar());
	        tmp -= r.getAmtSugar();
	        if (tmp != inventory.getSugar()) {
	        	throw new RuntimeException();
			}
	        tmp = inventory.getChocolate();
	        inventory.setChocolate(inventory.getChocolate() - r.getAmtChocolate());
	        tmp -= r.getAmtChocolate();
	        if (tmp != inventory.getChocolate()) {
	        	throw new RuntimeException();
			}
            return amtPaid - r.getPrice();
        }
        else {
            return amtPaid;
        }        
    }

    /**
     * Returns an array of all the recipes
    
     * @return Recipe[] 
     * @throws CloneNotSupportedException */
    public final  Recipe[] getRecipes() throws CloneNotSupportedException {  
    	
    	final Recipe [] reps = new Recipe[recipeArray.length] ;
    	int i = 0;
    	while (i < reps.length) {
    		reps[i] = (Recipe) recipeArray[i].clone();
    		i++;
		}    	
    	
        return  reps;
    }

    /**
     * Returns the Recipe associated with the given name
     * @param name
    
     * @return Recipe */
	public Recipe getRecipeForName(String name) {
		Recipe r = new Recipe();
		for(int i = 0; i < NUM_RECIPES; i++) {
			if(recipeArray[i].getName() != null) { 
				if((recipeArray[i].getName()).equals(name)) {
					r = recipeArray[i];
				}
			}
		}
		return r;
	}
	private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException{
       // stream.defaultReadObject();
	}
	
	protected Object clone() throws CloneNotSupportedException {
   	 
        final CoffeeMaker clone=(CoffeeMaker)super.clone();
        
        clone.recipeArray = recipeArray.clone();
        clone.inventory = (Inventory)inventory.clone();
        
        return clone;
        
    }	
}
