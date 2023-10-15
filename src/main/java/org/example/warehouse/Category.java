package org.example.warehouse;

import java.util.HashMap;
import java.util.Objects;

public class Category {

    private final String name;

    private static final HashMap<String, Category> categories = new HashMap<>();

    // ?? use with exception
    private Category(String name) {
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    // * Declare a public method that returns the name of the Category
    public String getName() {
        return name;
    }

    public static Category of(String name) {
        // Check if the name parameter is null
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");

        if (!categories.containsKey(name)) //***
            categories.put(name, new Category(name)); //***

        // Check if the categories HashMap already contains a Category instance with the same name
        if (categories.containsKey(name))
            // Return the existing Category instance from the HashMap
            return categories.get(name);

            // Create a new Category instance with the name parameter
        Category category = new Category(name);
        categories.put(name, category);
        return category;
        }

    // * Override the equals method to compare two Category instances by their names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        //return name.equals(category.name); //unclear which return is correct to use here
        return Objects.equals(name, category.name);//from google

    }
//
    // * Override the hashCode method to generate a hash code based on the name of the Category
    @Override
    public int hashCode() {
        return name.hashCode();
    }

//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }

}