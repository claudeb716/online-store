
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
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
    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        for (Product p : inventory){ //loop each product
            System.out.printf(p.getId() + p.getName() + p.getPrice()); // display products
        }
        System.out.println("Enter Product(ID) to Add to cart:");
        String idMatch = scanner.nextLine(); // save user input to string
        Product foundProduct = (findProductById(idMatch,inventory)); //create matching product from findProduct
        if (foundProduct != null){ // if condition check is not empty
            cart.add(foundProduct); // add to cart list
            inventory.remove(foundProduct); // remove from inventory
            System.out.println(foundProduct.getName() + " Added to cart! "); // print message it was added to cart
        }else{
            System.out.println("Entered ID doesn't match Product ID");
        }


    }



    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
        double total = 0.0; // initialize
        for (Product p : cart){ // loop Products in cart
            System.out.println(p.getId()+p.getName()+p.getPrice());// display cart products
            total += p.getPrice();// save price total
            System.out.println("Total of Cart: $" + total);// display total
        }
        System.out.println("Click (C)Checkout or (X)Return");
        String choice = scanner.nextLine(); //save user choice
        if (choice.equalsIgnoreCase("X")){ // if condition is user input match String
            return;
        }
        if (choice.equalsIgnoreCase("C")){ // if condition is user input match String
            checkOut(cart,total,scanner); // call checkOut method
        }

    }
    public static void checkOut(ArrayList<Product> cart, double totalAmount, Scanner scanner) {
        System.out.println("are you sure your ready to checkout? Yes(1) or No(2)");
        double choice = scanner.nextDouble(); // save input of choice
        if (choice == 2){ //if condition equal to 2 and return
            return;
        }
        if (choice == 1){ //if condition equal to 1
            System.out.println("Add payment(30.00)");
            double payment = scanner.nextInt();// payment input
            scanner.nextLine(); // catch next line
            double change = payment - totalAmount; // subtract payment from cart total
            System.out.println("Your Change: $" + change); // display results
            cart.clear(); // clear cart
        }
    }
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

