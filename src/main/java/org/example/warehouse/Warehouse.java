package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private String warehouse;

    // test 1. A private static field of the same type as the class
    private static Warehouse instance; // for test 1

    // test 1. A private constructor that takes no parameters
    private Warehouse() {} // for test 1

    // test 3. A private constructor that takes a name as a parameter
    public Warehouse(String warehouse) {
        this.warehouse = warehouse;
    }




    // test 3. A public static method that returns a new instance of Warehouse with the given name
    public static Warehouse getInstance(String warehouse) {
        return new Warehouse(warehouse);
    }


    // A public static method that returns the instance of the class
    public static Warehouse getInstance() { // for test 1
        // Check if the instance is null
        if (instance == null) {
            // Create a new instance and assign it to the field
            instance = new Warehouse();
        }
        return instance;
    }

    private static Map<String, Warehouse> warehouseMap = new HashMap<>();

    // create array to hold products
    public List<ProductRecord> productRecords = new ArrayList<>();
    public final List<ProductRecord> productRecordsModified = new ArrayList<>();


    public boolean isEmpty() {
        //warehouse.isEmpty();
        return getInstance().isEmpty();
    }

//    public boolean getProducts() {
//        return (warehouse.getProducts()).isEmpty();
//    }


//   public boolean getProducts() { //need changes
//        return getProducts();
//   }*/

//   // create array to hold products - see use before productRecord
    //public List<ProductRecord> products = new ArrayList<>();
    //public final List<ProductRecord> productsModified = new ArrayList<>();

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(productRecords);
    }


    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null || category.toString().isEmpty()) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null || uuid.toString().isEmpty()) {
            uuid = UUID.randomUUID();
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }

        ProductRecord productRecord = new ProductRecord(uuid, name, category, price);
        // Add the product record to the productRecords list
        productRecords.add(productRecord);
        return productRecord;
         }

    public List<ProductRecord> getProductById(UUID uuid) {
        productRecords.stream()
            .filter(product -> product.getUuid().equals(uuid)).findFirst();
            return productRecords;
    }


//    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
//        Map<Category, List<ProductRecord>> productsOfCategories = productRecords.stream()
//                .collect(Collectors.groupingBy(ProductRecord::category));
//    }
}