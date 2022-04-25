package ru.geekbrains.persist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private long cost;

    public Product() {
    }

    public Product(String title) { this.title = title; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public long getCost() { return cost; }
}
