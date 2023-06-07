/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 *
 * Modifications
 * 20171114 - Ian De Silva - Updated to comply with JUnit 4 and to adhere to
 * 							 coding standards.  Added test documentation.
 */
package edu.ncsu.csc326.coffeemaker;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Sarah Heckman
 */
public class CoffeeMakerTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;
    private Recipe recipeEdit;


    Inventory inv = new Inventory();

    // BEFORE METHOD

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     *                         amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for r1

        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
        //Set up for myRes1
        recipeEdit = new Recipe();
        recipeEdit.setName("Editable");
        recipeEdit.setAmtChocolate("0");
        recipeEdit.setAmtCoffee("0");
        recipeEdit.setAmtMilk("0");
        recipeEdit.setAmtSugar("0");
        recipeEdit.setPrice("1");

    }

    /** addInventory TESTS
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    //OK Recipe
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "1", "1");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quanity
     *                            to a positive integer.
     */
    //Negative Coffee
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionCoffee() throws InventoryException {
        coffeeMaker.addInventory("-4", "1", "1", "1");
    }

    //Word Coffee
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionCoffee2() throws InventoryException {
        coffeeMaker.addInventory("asdf", "1", "1", "1");
    }
    //Negative Milk
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionMilk() throws InventoryException {
        coffeeMaker.addInventory("1", "-1", "1", "1");
    }

    //Word Milk
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionMilk2() throws InventoryException {
        coffeeMaker.addInventory("1", "asdf", "1", "1");
    }

    //Negative Sugar
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionSugar() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "-1", "1");
    }

    //Word Sugar
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionSugar2() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "asdf", "1");
    }

    //Negative Chocolate
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionChocolate() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "0", "-1");
    }

    //Word Chocolate
    @Test(expected = InventoryException.class)
    public void testAddInventoryExceptionChocolate2() throws InventoryException {
        coffeeMaker.addInventory("1", "1", "0", "asdf");
    }

    /**
     * Inventory Setter TESTS
     */

    //Coffee setter
    @Test
    public void coffeeSetterTestNegative(){
        inv.setCoffee(1);
        inv.setCoffee(-2);
        assertEquals(1,inv.getCoffee());
    }

    //Milk setter
    @Test
    public void milkSetterTestNegative(){
        inv.setMilk(1);
        inv.setMilk(-2);
        assertEquals(1,inv.getMilk());
    }

    //Sugar setter
    @Test
    public void sugarSetterTestNegative(){
        inv.setSugar(1);
        inv.setSugar(-2);
        assertEquals(1,inv.getSugar());
    }

    //Chocolate Setter
    @Test
    public void chocolateSetterTestNegative(){
        inv.setChocolate(1);
        inv.setChocolate(-2);
        assertEquals(1,inv.getChocolate());
    }

    /**
     * addRecipe TESTS
     */

    //Test OK Add Recipe
    @Test
    public void testAddRecipeOK() {
        coffeeMaker.addRecipe(recipe1);
        assertTrue(coffeeMaker.addRecipe(recipe1));
    }

    //Test addRecipe checking for existing names
    @Test
    public void testAddRecipeDublNames() {
        coffeeMaker.addRecipe(recipe1);
        assertFalse(coffeeMaker.addRecipe(recipe1));
    }

    //Test addRecipe book is full
    @Test
    public void testAddRecipeFullBook() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);
        assertFalse(coffeeMaker.addRecipe(recipeEdit));
    }

    /**
     * deleteRecipe TESTS
     *
     * @throws RecipeException
     */

    //Test OK Delete
    @Test
    public void testDeleteRecipes() throws RecipeException {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.deleteRecipe(0);
        assertEquals(null, coffeeMaker.getRecipes()[0].getName());

    }

    //Test Delete Empty Book
    @Test
    public void testDeleteWithoutRecipes() {
        assertEquals(null, coffeeMaker.deleteRecipe(0));
    }

    /**
     * EDIT RECIPE TESTS
     */

    //Test OK Edit
    @Test
    public void testEditRecipe() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.editRecipe(0, recipe2);
        assertEquals(recipe2.getName(), coffeeMaker.getRecipes()[0].getName());
    }

    //Test Edit Empty Book
    @Test
    public void testEditWithoutRecipe() {
        assertEquals(null, coffeeMaker.editRecipe(0, recipe1));
    }

    /**
     * BUY COFFEE TESTS
     * /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * the coffee costs
     * Then we get the correct change back.
     */

    //Test OK Buy Coffee
    @Test
    public void testMakeCoffee() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    /**
     * INVENTORY TESTS
     */

    /**
     * TESTS not enough ingredients for 2 coffee
     */

    //OK Coffee
    @Test
    public void testOKCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getCoffee()-1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Coffee
    @Test
    public void testMissingCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getCoffee() + 1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //OK Chocolate
    @Test
    public void testOKChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getChocolate()-1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Chocolate
    @Test
    public void testMissingChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtChocolate(Integer.toString(inv.getChocolate() + 1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //OK Sugar
    @Test
    public void testOKSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getSugar()-1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Sugar
    @Test
    public void testMissingSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtSugar(Integer.toString(inv.getSugar() + 1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //OK Milk
    @Test
    public void testOKMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getMilk()-1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Milk
    @Test
    public void testMissingMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtMilk(Integer.toString(inv.getMilk() + 1));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Coffee for two orders
    @Test
    public void testTwoOrdersCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(inv.getCoffee()/2+1));

        assertEquals(99, coffeeMaker.makeCoffee(0, 100));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));
    }

    //Not enough Chocolate for two orders
    @Test
    public void testTwoOrdersChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtChocolate(Integer.toString(inv.getChocolate()/2+1));

        assertEquals(99, coffeeMaker.makeCoffee(0, 100));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Sugar for two orders
    @Test
    public void testTwoOrdersSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtSugar(Integer.toString(inv.getSugar()/2+1));

        assertEquals(99, coffeeMaker.makeCoffee(0, 100));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    //Not enough Milk for two orders
    @Test
    public void testTwoOrdersMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtMilk(Integer.toString(inv.getMilk()/2+1));

        assertEquals(99, coffeeMaker.makeCoffee(0, 100));
        assertEquals(100, coffeeMaker.makeCoffee(0, 100));

    }

    /**
     * Class CoffeeMaker
     * makeCoffee TESTS
     */

    @Test
    public void OkRecipeToPurchaseTest(){
        coffeeMaker.addRecipe(recipe1);
        assertEquals(100, coffeeMaker.makeCoffee(1, 100));
    }


    //missing recipe
    @Test
    public void noRecipeToPurchaseTest(){

        assertEquals(100, coffeeMaker.makeCoffee(1, 100));
    }

    //not enough paid
    @Test
    public void notEnoughPaidTest(){
        coffeeMaker.addRecipe(recipe1);
        assertEquals(1, coffeeMaker.makeCoffee(0, 1));
    }

    /**
     * Class CoffeeMaker
     * checkInventory TEST
     */

    //checkInventory TEST
    @Test
    public void checkInventoryTest(){
        assertEquals(coffeeMaker.checkInventory(), inv.toString());
    }


    /**
     * Class Recipe
     * Recipe Setters TESTS
     * @throws RecipeException
     */

    //ok Coffee
    @Test
    public void testOkCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee("1");
        assertEquals(1, recipeEdit.getAmtCoffee());
    }

    //negative Coffee
    @Test(expected = RecipeException.class)
    public void testNegativeCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee(Integer.toString(-1));
    }

    //word Coffee
    @Test(expected = RecipeException.class)
    public void testWordCoffee() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtCoffee("asdf");
    }

    //ok Chocolate
    @Test
    public void testOkChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtChocolate("1");
        assertEquals(1, recipeEdit.getAmtChocolate());
    }

    //negative Chocolate
    @Test(expected = RecipeException.class)
    public void testNegativeChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtChocolate(Integer.toString(-1));
    }

    //word Chocolate
    @Test(expected = RecipeException.class)
    public void testWordChocolate() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtChocolate("asdf");
    }

    //ok Sugar
    @Test
    public void testOkSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtSugar("1");
        assertEquals(1, recipeEdit.getAmtSugar());
    }

    //negative Sugar
    @Test(expected = RecipeException.class)
    public void testNegativeSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtSugar(Integer.toString(-1));
    }

    //word Sugar
    @Test(expected = RecipeException.class)
    public void testWordSugar() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtSugar("asdf");
    }

    //ok Milk
    @Test
    public void testOkMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtMilk("1");
        assertEquals(1, recipeEdit.getAmtMilk());
    }

    //negative Milk
    @Test(expected = RecipeException.class)
    public void testNegativeMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtMilk(Integer.toString(-1));
    }

    //word Milk
    @Test(expected = RecipeException.class)
    public void testWordMilk() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setAmtMilk("asdf");
    }

    //ok Price
    @Test
    public void testOkPrice() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setPrice("1");
        assertEquals(1, recipeEdit.getPrice());
    }

    //negative Price
    @Test(expected = RecipeException.class)
    public void testNegativePrice() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setPrice(Integer.toString(-1));
    }

    //word Price
    @Test(expected = RecipeException.class)
    public void testWordPrice() throws RecipeException {
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setPrice("asdf");
    }

    //empty Name
    @Test
    public void testEmptyName(){
        coffeeMaker.addRecipe(recipeEdit);
        recipeEdit.setName(null);
    }

    /**
     * Class Main
     */

    /**
     * Class Recipe
     * Questionable TESTS
     */

    //test hashCode
    @Test
    public void testHashCodeBranch(){
        Recipe recipeTest = new Recipe();
        int result = 31 * 1 + ((recipe1.getName() == null) ? 0 : recipe1.getName().hashCode());
        assertEquals(result,recipe1.hashCode());
        result = 31 * 1 + ((recipeTest.getName() == null) ? 0 : recipeTest.getName().hashCode());
        assertEquals(result,recipeTest.hashCode());
    }


    //test equals
    @Test
    public void testEqualsBranch(){
        coffeeMaker.addRecipe(recipe1);
        assertNotEquals(inv, recipe1);
    }


    /**Main TESTS
     *
     */

    @Test
    public void testMain(){
        String input = "name\n1\n1\n1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String expectedOutput = "";


    }
}
