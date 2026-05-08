
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * Starter code for the Online Store workshop.
 * Students will complete the TODO sections to make the program work.
 */

public class Store {

    public static void main(String[] args) {

        // Create lists for inventory and the shopping cart
        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        // Load inventory from the data file (pipe-delimited: id|name|price)
        loadInventory("products.csv", inventory);

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                    // clear newline

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    /**
     * Reads product data from a file and populates the inventory list.
     * File format (pipe-delimited):
     * id|name|price
     * <p>
     * Example line:
     * A17|Wireless Mouse|19.99
     */
    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        // TODO: read each line, split on "|",
        //       create a Product object, and add it to the inventory list

        //Input empty String and while loop to run each line of string if NOT null.
        //Add buffered reader to read file
        String line; //empty string to help with loop
        try {BufferedReader br = new BufferedReader(new FileReader(fileName)); //Read file

            while ((line = br.readLine()) != null) {  //Loop reading each line of string in file until it's reaches null
                String[] parts = line.split("\\|"); // Split data on file with "|"
                String id = parts[0].trim();//Save as a String and trim extra white space
                String name = parts[1].trim(); // Save as a String and trim extra white space
                double price = Double.parseDouble(parts[2]); // Parse String into a Double
                Product product = new Product(id,name,price);// Save Data to Class
                inventory.add(product); // Add new Product(parts) to inventory ArrayList
            }
            br.close(); // Close Reader
        } catch (IOException e) { // error message
            System.err.println("File Not Found " + e.getMessage());
        }
    }

    /**
     * Displays all products and lets the user add one to the cart.
     * Typing X returns to the main menu.
     */
    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        // TODO: show each product (id, name, price),
        //       prompt for an id, find that product, add to cart
        //Loop
        for (Product p : inventory){ //loop each product
            System.out.printf(p.getId() + p.getName() + p.getPrice()); // display products
            String idMatch = scanner.nextLine(); // save user input to string
            Product foundProduct = (findProductById(idMatch,inventory)); //create matching product from findProduct
            if (foundProduct != null){ // if conditon check if not empty
            cart.add(foundProduct); // add to cart list
            inventory.remove(foundProduct); // remove from inventory
                System.out.println(foundProduct.getName() + " Added to cart! "); // print message it was added to cart
            }else{
                System.out.println("Entered ID doesn't match Product ID");
            }
        }
        System.out.println("Enter Product(ID) to Add to cart:");
    }


    /**
     * Shows the contents of the cart, calculates the total,
     * and offers the option to check out.
     */
    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
        // TODO:
        //   • list each product in the cart
        //   • compute the total cost
        //   • ask the user whether to check out (C) or return (X)
        //   • if C, call checkOut(cart, totalAmount, scanner)
    }

    /**
     * Handles the checkout process:
     * 1. Confirm that the user wants to buy.
     * 2. Accept payment and calculate change.
     * 3. Display a simple receipt.
     * 4. Clear the cart.
     */
    public static void checkOut(ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {
        // TODO: implement steps listed above
    }

    /**
     * Searches a list for a product by its id.
     *
     * @return the matching Product, or null if not found
     */
    public static Product findProductById(String id, ArrayList<Product> inventory) {
        // TODO: loop over the list and compare ids

        for (Product p : inventory){ //loop through products
            Product matchedId; //value holder
            if (p.getId().equalsIgnoreCase(id)){ // if product id match String id
                matchedId = new Product(p.getId(), p.getId(), p.getPrice()); // save product that matches
                return matchedId; // return matching product
            }
        }return null;

    }

}

