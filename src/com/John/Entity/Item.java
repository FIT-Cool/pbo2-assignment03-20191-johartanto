package com.John.Entity;
/**
 * author:Johnson Hartanto
 * NRP:1772017
 */
import java.time.LocalDate;
import java.util.Date;

public class Item {
    private String name;
    private int id;
    private Category category;
    private LocalDate exDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getExDate() {
        return exDate;
    }

    public void setExDate(LocalDate exDate) {
        this.exDate = exDate;
    }
}
