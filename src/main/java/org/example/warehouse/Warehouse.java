package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private String name;
    private List<ProductRecord> productList; // array to hold products
    private List<ProductRecord> productListChanged;


    private static Warehouse instance;

    private Warehouse() {
    }

    private Warehouse(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
        this.productListChanged = new ArrayList<ProductRecord>();
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse("MyStore");
        }
        return instance;
    }

    public boolean isEmpty() {
        //return warehouse.isEmpty();
        return getProducts().isEmpty();
    }

    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        if ((name == null) || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if(getProductById(uuid).isPresent()) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        if (price == null) {
            price = BigDecimal.ZERO;
        }

        ProductRecord productRecord = new ProductRecord(uuid, name, category, price);
        productList.add(productRecord);
        return productRecord;
    }

    public List<ProductRecord> getProducts() {
        return List.copyOf(productList);
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {
        return productList.stream()
                .filter(product -> product.getUuid().equals(uuid))
                .findFirst();
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        Map<Category, List<ProductRecord>> productsGroupedByCategories = productList.stream()
                .collect(Collectors.groupingBy(ProductRecord::getCategory));
        return productsGroupedByCategories;
    }

    public void updateProductPrice(UUID uuid, BigDecimal updatedPrice) {
        Optional<ProductRecord> productRecord = productList.stream()
                .filter(product -> product.uuid().equals(uuid))
                .findFirst();
        if (productRecord.isPresent()) {
            ProductRecord productUpdated = productRecord.get();
            productUpdated.setPrice(updatedPrice);
            productListChanged.add(productUpdated);
            } else
            throw new IllegalArgumentException("Product with that id doesn't exist.");
    }

    public List<ProductRecord> getChangedProducts() {
        return List.copyOf(productListChanged);
    }
}


