package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

//check if class or record should be used
public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    public BigDecimal price;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {
        if(uuid==null) {
            this.uuid = UUID.randomUUID();
        } else this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() { // *** check if required
        return name;
    }

    public Category getCategory() {
        return category();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID uuid() {
        return uuid;
    }

    public Category category(){
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRecord that = (ProductRecord) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, category, price);
    }


    public void price (BigDecimal price){
        this.price = price;
    }
    }





