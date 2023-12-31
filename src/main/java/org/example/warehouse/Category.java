package org.example.warehouse;

import java.util.HashMap;
import java.util.Objects;

public class Category {

    private final String name;

    private static final HashMap<String, Category> categories = new HashMap<>();

    private Category(String name) {
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    public static Category of(String name) {
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");

        if (!categories.containsKey(name))
            categories.put(name, new Category(name));

        if (categories.containsKey(name))
            return categories.get(name);

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
        return Objects.equals(name, category.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
