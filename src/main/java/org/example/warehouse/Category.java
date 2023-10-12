package org.example.warehouse;

import java.util.HashMap;
import java.util.Objects;

// Define the Category class
public class Category {
    // * Declare a private final String field to store the name of the Category
    private final String name;

    // * Declare a private static final HashMap to store the Category instances by name
    private static final HashMap<String, Category> categories = new HashMap<>();


    // ?? use with exception
    // * Declare a private constructor that takes a String parameter and assigns it to the name field
    private Category(String name) {
        // Convert the name parameter to uppercase first letter and lowercase rest of the letters
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    // * Declare a public method that returns the name of the Category
    public String getName() {
        return name;
    }

    // Declare a public static method that takes a String parameter and returns a Category instance
    public static Category of(String name) {
        // Check if the name parameter is null and throw an IllegalArgumentException if it is
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        // Convert the name parameter to uppercase first letter and lowercase rest of the letters
        //name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        // Check if the categories HashMap already contains a Category instance with the same name
        if (categories.containsKey(name)) {
            // Return the existing Category instance from the HashMap
            return categories.get(name);
        } else {
            // Create a new Category instance with the name parameter
            Category category = new Category(name);
            // Put the new Category instance into the HashMap with the name as the key
            categories.put(name, category);
            // Return the new Category instance
            return category;
        }
    }

    // * Override the equals method to compare two Category instances by their names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    // * Override the hashCode method to generate a hash code based on the name of the Category
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}