package org.example.warehouse;

public class Warehouse {
    private String warehouse;

    private Warehouse() {}

    public Warehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public static Warehouse getInstance(String warehouse) {
        return new Warehouse(warehouse);
    }

    public static Warehouse getInstance() {
        return new Warehouse();
    }

    public boolean isEmpty() {
    }


//    public static Warehouse getInstance(String newWarehouse) {
//        if(newWarehouse == null
//    }
//    private final List<ProductRecord> addedProducts;
//    private final List<ProductRecord> changedProducts;
//    private static Warehouse instance;




}