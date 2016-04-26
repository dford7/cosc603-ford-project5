package edu.towson.cis.cosc603.project5.coffeemaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

/**
 * Starts the console UI for the CoffeeMaker
 * @author Josh
 * @version $Revision: 1.0 $
 */
public class Main implements Cloneable{
    private static CoffeeMaker coffeeMaker;

    public static void mainMenu() throws CloneNotSupportedException {
        System.out.println("1. Add a recipe"
        		+"2. Delete a recipe" + System.getProperty("line.separator")
        		+"3. Edit a recipe" + System.getProperty("line.separator")
        		+"4. Add inventory" + System.getProperty("line.separator")
        		+"5. Check inventory" + System.getProperty("line.separator")
        		+"6. Make coffee" + System.getProperty("line.separator")
        		+ "0. Exit" + System.getProperty("line.separator") );
        
        //Get user input
        final int userInput = stringToInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
        
        switch (userInput) {
		case 1:
			addRecipe();
			break;
		case 2:
			deleteRecipe();
			break;
		case 3:
			editRecipe();
			break;
		case 4:
			addInventory();
			break;
		case 5:
			checkInventory();
			break;
		case 6:
			makeCoffee();
			break;		
		case 0:
			System.exit(0);
			break;			
		default:
			break;
		}       
    }
	public static void addRecipe() throws CloneNotSupportedException {
	    //Read in recipe name
		final String name = inputOutput(System.getProperty("line.separator")  + "Please enter the recipe name: ");
	    
	    //Read in recipe price
		final String priceString = inputOutput(System.getProperty("line.separator")  + "Please enter the recipe price: $");
		final int price = stringToInt(priceString);
	    if(price < 0) {
	    	return ;
	    }
	    
	    //Read in amt coffee
	    final String coffeeString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of coffee in the recipe: ");
	    final int amtCoffee = stringToInt(coffeeString);
	    if(amtCoffee < 0) {
	    	return ;
	    }
	    
	    //Read in amt milk
	    final String milkString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of milk in the recipe: ");
	    final int amtMilk = stringToInt(milkString);
	    if(amtMilk < 0) {
	    	return ;
	    }
	    
	    //Read in amt sugar
	    final String sugarString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of sugar in the recipe: ");
	    final int amtSugar = stringToInt(sugarString);
	    if(amtSugar < 0) {
	    	return ;
	    }
	    
	    //Read in amt chocolate
	    final String chocolateString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of chocolate in the recipe: ");
	    final int amtChocolate = stringToInt(chocolateString);
	    if(amtChocolate < 0) {
	    	return ;
	    }
	    
	 //   boolean recipeAdded = false;
	    final Recipe r = new Recipe(name, price, amtChocolate, 
				amtMilk, amtSugar, amtChocolate);
	/*	r.setName(name);
		r.setPrice(price);
		r.setAmtCoffee(amtCoffee);
		r.setAmtMilk(amtMilk);
		r.setAmtSugar(amtSugar);
		r.setAmtChocolate(amtChocolate);
		  */ 
	    final boolean recipeAdded = coffeeMaker.addRecipe(r);
	    
	    if(recipeAdded) {
	    	System.out.println(name + " successfully added.");
	    }
	    else {
	    	System.out.println(name + "could not be added.");
	    }
	   // mainMenu();
    }
    
    public static void deleteRecipe() throws CloneNotSupportedException {
    	final Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
            System.out.println((i+1) + ". " + recipes[i].getName());
        }
        final String recipeToDeleteString = inputOutput("Please select the number of the recipe to delete.");
        final int recipeToDelete = stringToInt(recipeToDeleteString) - 1;
	    if(recipeToDelete < 0) {
	    	return ;
	    }
        
	    final boolean recipeDeleted = coffeeMaker.deleteRecipe(recipes[recipeToDelete]);
        
        if(recipeDeleted) 
        	{
        	System.out.println(recipes[recipeToDelete].getName() + " successfully deleted.");
        	}
	    else 
	    	{
	    	System.out.println(recipes[recipeToDelete].getName() + "could not be deleted.");
	    	}
        
      //  mainMenu();
    }
    
    public static void editRecipe() throws CloneNotSupportedException {
    	final Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
            System.out.println((i+1) + ". " + recipes[i].getName());
        }
        final String recipeToEditString = inputOutput("Please select the number of the recipe to edit.");
        final int recipeToEdit = stringToInt(recipeToEditString) -1;
	    if(recipeToEdit < 0) {
	    	return ;
	    }
        
	    final Recipe oldRecipe = recipes[recipeToEdit];
        
	    //Read in recipe name
	    final String name = inputOutput(System.getProperty("line.separator")  + "Please enter the recipe name: ");
	    
	    //Read in recipe price
	    final String priceString = inputOutput(System.getProperty("line.separator")  + "Please enter the recipe price: $");
	    final int price = stringToInt(priceString);
	    if(price < 0) {
	    	return ;
	    }
	    
	    //Read in amt coffee
	    final String coffeeString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of coffee in the recipe: ");
	    final int amtCoffee = stringToInt(coffeeString);
	    if(amtCoffee < 0) {
	    	return ;
	    }
	    
	    //Read in amt milk
	    final String milkString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of milk in the recipe: ");
	    final int amtMilk = stringToInt(milkString);
	    if(amtMilk < 0) {
	    	return ;
	    }
	    
	    //Read in amt sugar
	    final String sugarString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of sugar in the recipe: ");
	    final int amtSugar = stringToInt(sugarString);
	    if(amtSugar < 0) {
	    	return ;
	    }
	    
	    //Read in amt chocolate
	    final String chocolateString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of chocolate in the recipe: ");
	    final int amtChocolate = stringToInt(chocolateString);
	    if(amtChocolate < 0) {
	    	return ;
	    }
	    final Recipe newRecipe = new Recipe(name, price, amtChocolate, 
				amtMilk, amtSugar, amtChocolate);
	  /*  Recipe newRecipe = new Recipe();
	    newRecipe.setName(name);
	    newRecipe.setPrice(price);
	    newRecipe.setAmtCoffee(amtCoffee);
	    newRecipe.setAmtMilk(amtMilk);
	    newRecipe.setAmtSugar(amtSugar);
	    newRecipe.setAmtChocolate(amtChocolate);
        */
	    final boolean recipeEdited = coffeeMaker.editRecipe(oldRecipe, newRecipe);
        
        if(recipeEdited)
        	{
        	System.out.println(oldRecipe.getName() + " successfully edited.");
        	}
	    else 
	    	{
	    	System.out.println(oldRecipe.getName() + "could not be edited.");
	    	}
        
       // mainMenu();
    }
    
    public static void addInventory() throws CloneNotSupportedException {
	    //Read in amt coffee
    	final String coffeeString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of coffee to add: ");
    	final int amtCoffee = stringToInt(coffeeString);
	    if(amtCoffee < 0) {
	    	return ;
	    }
	    
	    //Read in amt milk
	    final String milkString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of milk to add: ");
	    final int amtMilk = stringToInt(milkString);
	    if(amtMilk < 0) {
	    	return ;
	    }
	    
	    //Read in amt sugar
	    final String sugarString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of sugar to add: ");
	    final int amtSugar = stringToInt(sugarString);
	    if(amtSugar < 0) {
	    	//mainMenu();
	    	return ;
	    }
	    
	    //Read in amt chocolate
	    final String chocolateString = inputOutput(System.getProperty("line.separator")  + "Please enter the units of chocolate to add: ");
	    final int amtChocolate = stringToInt(chocolateString);
	    if(amtChocolate < 0) {
	    //	mainMenu();
	    	return ;
	    }
	    
        coffeeMaker.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);
      //  mainMenu();
    }
    
    public static void checkInventory() throws CloneNotSupportedException {
    	final Inventory inventory = coffeeMaker.checkInventory();
        System.out.println(inventory.toString());
      //  mainMenu();
    }
    
    public static void makeCoffee() throws CloneNotSupportedException {
    	final Recipe [] recipes = coffeeMaker.getRecipes();
        for(int i = 0; i < recipes.length; i++) {
            System.out.println((i+1) + ". " + recipes[i].getName());
        }
        final String recipeToPurchaseString = inputOutput("Please select the number of the recipe to purchase.");
        final int recipeToPurchase = stringToInt(recipeToPurchaseString) -1;
	    if(recipeToPurchase < 0) {
	    //	mainMenu();
	    	return ;
	    }
        
	    final String amountPaid = inputOutput("Please enter the amount you wish to pay");
	    final int amountToPay = stringToInt(amountPaid);
	    if(amountToPay < 0) {
	    //	mainMenu();
	    	return ;
	    }
        
	    final Recipe recipe = recipes[recipeToPurchase];
	    final int change = coffeeMaker.makeCoffee(recipe, amountToPay);
        
        System.out.println("Your change is: " + change + System.getProperty("line.separator")  + "");
      //  mainMenu();
    }
    
    /**
     * Method inputOutput.
     * @param message String
     * @return String
     * @throws CloneNotSupportedException 
     */
    public static String inputOutput(String message) throws CloneNotSupportedException {
        System.out.println(message);
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
	    try {	    	
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	    	e.printStackTrace();
	        System.out.println("Error reading in value");
	        mainMenu();
	    }
	    return returnString;
    }
    
    /**
     * Method stringToInt.
     * @param value String
     * @return int
     */
    private static int stringToInt(String value) {
        int returnInt = -1;
        try {
            returnInt = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
        	e.printStackTrace();
            System.out.println("Please input an integer" + System.getProperty("line.separator") );
        }
        return returnInt;
    }
    
    /**
     * Method main.
     * @param args String[]
     * @throws CloneNotSupportedException 
     */
    public static void main(String[] args) throws CloneNotSupportedException {
	    coffeeMaker = new CoffeeMaker();
	    System.out.println("Welcome to the CoffeeMaker!" + System.getProperty("line.separator") );
	 //   mainMenu();
	}
    
    private void readObject(ObjectInputStream stream) 
            throws IOException, ClassNotFoundException{
     //   stream.defaultReadObject();
	}
}
