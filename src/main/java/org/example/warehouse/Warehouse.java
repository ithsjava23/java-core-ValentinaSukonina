package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    public boolean isEmpty() {
        return warehouse.isEmpty();
        //return getInstance().isEmpty();
    }

    // create array to hold products
    public List<ProductRecord> productList = new ArrayList<>();
    public List<Optional<ProductRecord>> productListChanged = new ArrayList<>();

    public List<ProductRecord> getProducts() {
        //return Collections.unmodifiableList(productList);
        return List.copyOf(productList);
    }
//    public List<ProductRecord> getChangedProducts() {
//        //return new ArrayList<>(productListChanged);
//        return productListChanged;
//    }



    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null || uuid.toString().isEmpty()) {
            uuid = UUID.randomUUID();
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }

        //ProductRecord productRecord = new ProductRecord(uuid, name, category, price);
        // Add the product record to the productRecords list
        //productList.add(productRecord);
//        return productRecord;
        return new ProductRecord(uuid, name, category, price);
         }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .findFirst();
    }


//    public List<ProductRecord> getProductsBy(Category meat) {
//        return productList.stream()
//                .filter(product -> product.getCategory().equals(c))
//    }
//
//
//    public List<ProductRecord> getProductByVS(Category category) {
//        return productList.stream()
//                .filter(product -> product.getCategory().equals(category))
//                .collect(Collectors.toList());
//    }

//    public List<ProductRecord> getProductsBy(Category category) {
//        List<ProductRecord> productsByCategory = new ArrayList<>();
//        for (ProductRecord  ::category)
//        for (ProductRecord : ) {
//            if (product.getCategory().equals(category)) {
//                productsByCategor.add(product);
//                }
//            }
//            return result;

//        public List<ProductRecord> getProductsBy(Category category) {
//            List<ProductRecord> productsByCategory = new ArrayList<>();
//            for (ProductRecord:category)
//                 ) {
//
//            }
//
//        }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories () {
        Map<Category, List<ProductRecord>> productsGroupedByCategories = productList.stream()
                .collect(Collectors.groupingBy(ProductRecord::getCategory));
            return productsGroupedByCategories;
    }

   public void updateProductPrice(UUID uuid, BigDecimal updatedPrice) {
   Optional<ProductRecord> productRecord = productList.stream()
           .filter(product -> product.uuid().equals(uuid))
           .findFirst();
   if (productRecord.isPresent()) {
       productRecord.get().setPrice(updatedPrice);
       productListChanged.add(productRecord);
   }
   else
       throw new IllegalArgumentException("No such product was found in record");
    }


}