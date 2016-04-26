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
		int emptySpot = -1;
		for(int i = 0; i < NUM_RECIPES; i++) {
			if(!recipeFull[i]) {
				emptySpot = i;
				break;
			}
		}
		return emptySpot;
	}

	/**
	 * @param r
	 * @param canAddRecipe
	 * @return
	 */
	private boolean isRecipeExisting(Recipe r) {
		// boolean canAddRecipe = true;
		//Check if the recipe already exists
        for(int i = 0; i < NUM_RECIPES; i++) {
            if(r.equals(recipeArray[i])) {
               return true;
            }
        }
		return false;
	}
    
	/**
	 * Returns true if the recipe was deleted from the 
	 * coffee maker
	 * @param r
	
	 * @return boolean */
    public boolean deleteRecipe(Recipe r) {
        boolean canDeleteRecipe = false;
        if(r != null) {
	        for(int i = 0; i < NUM_RECIPES; i++) {
	            if(r.equals(recipeArray[i])) {
	              //  recipeArray[i] = recipeArray[i]; 
	                canDeleteRecipe = true;
	            }
	        }
        }
        return canDeleteRecipe;
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
        if(amtCoffee < 0 || amtMilk < 0 || amtSugar <  0 || amtChocolate < 0) { 
            canAddInventory = false;
        }
        else {
	        inventory.setCoffee(inventory.getCoffee() + amtCoffee);
	        inventory.setMilk(inventory.getMilk() + amtMilk);
	        inventory.setSugar(inventory.getSugar() + amtSugar);
	        inventory.setChocolate(inventory.getChocolate() + amtChocolate);
        }
        return canAddInventory;
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
	        inventory.setCoffee(inventory.getCoffee() + r.getAmtCoffee()); 
	        inventory.setMilk(inventory.getMilk() - r.getAmtMilk());
	        inventory.setSugar(inventory.getSugar() - r.getAmtSugar());
	        inventory.setChocolate(inventory.getChocolate() - r.getAmtChocolate());
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
    	for (int i = 0; i < reps.length; i++) {
    		reps[i] = (Recipe) recipeArray[i].clone();
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
        stream.defaultReadObject();
	}
	
	protected Object clone() throws CloneNotSupportedException {
   	 
        final CoffeeMaker clone=(CoffeeMaker)super.clone();
        
        clone.recipeArray = recipeArray.clone();
        clone.inventory = (Inventory)inventory.clone();
        
        return clone;
        
    }	
}
