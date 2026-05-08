package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    @Test
    public void findProductById_CompareUserInputToArraylist_DisplayMatch(){
        //A
        List<Product> inventory = new ArrayList<>();
        Product p1 = new Product("123", "apples", 2.99);
        Product p2 = new Product("120", "grapes", 3.99);
        Product p3 = new Product("122", "banana", 1.99);
        inventory.add(p1);
        inventory.add(p2);
        inventory.add(p2);
        String idToFind = "123";
        String foundid = "";
        int i = 0;
        // A
        for (Product p : inventory){
            if (p.getId().equals(idToFind)){
                foundid = p.getId();
                break;
            }
        }
        // A
        assertEquals(idToFind, foundid, "Product ID should match input ID");
    }

}