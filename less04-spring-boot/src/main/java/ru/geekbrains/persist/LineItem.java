package ru.geekbrains.persist;

import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "line_items",
        indexes = {
                @Index(name = "ux_product_customer",
                        columnList = "product_id, customer_id, color, size",
                        unique = true
                )}
)
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")    // чтобы была уникальность
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")   // чтобы была уникальность
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long qty;

    @Column
    private String color;

    @Column
    private String size;

    public LineItem() {
    }

    public LineItem(Long id, Product product, Customer customer, BigDecimal price, Long qty, String color, String size) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.price = price;
        this.qty = qty;
        this.color = color;
        this.size = size;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
