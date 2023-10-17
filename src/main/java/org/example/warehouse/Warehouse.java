package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private String warehouse;
    public List<ProductRecord> productList; // array to hold products
    public List<ProductRecord> productListChanged;


    private static Warehouse instance;
    private Warehouse() {}

    public Warehouse(String warehouse) {
        this.warehouse = warehouse;
        this.productList = new ArrayList<>(productList);
        this.productListChanged = new ArrayList<>();
    }

    public static Warehouse getInstance(String warehouse) {
        return new Warehouse(warehouse);
    }
    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public boolean isEmpty() {
        return warehouse.isEmpty();
    }
    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        if ((name == null) || name.isEmpty()){
    throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null){
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (uuid == null){
            uuid = UUID.randomUUID();
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
                .filter(product ->product.getUuid().equals(uuid))
                .findFirst();
    }

    public List<ProductRecord> getProductsBy(Category category){
        return productList.stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

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
            if (productListChanged.contains(productRecord.get())) {
                productListChanged.add(productRecord.get());
            } else
                throw new IllegalArgumentException("No such product was found in record");
        }
    }
    public List<ProductRecord> getChangedProducts(){
        return List.copyOf(productListChanged);
    }
}


