package ru.geekbrains.persist;

public class Product {

    private Long id;
    private String productname;
    private Float price;

    public Product(String productname, Float price) {
        this.productname = productname;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) { this.productname = productname; }

    public Float getPrice() { return price; }

    public void setPrice(Float price) { this.price = price; }
}
