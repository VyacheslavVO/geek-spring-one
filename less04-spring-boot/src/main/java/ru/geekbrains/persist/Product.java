package ru.geekbrains.persist;

import javax.validation.constraints.NotBlank;

public class Product {

    private Long id;

    @NotBlank
    private String title;

    private long cost;

    public Product(String title) { this.title = title; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public long getCost() { return cost; }
}
