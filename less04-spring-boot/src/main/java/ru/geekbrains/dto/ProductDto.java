package ru.geekbrains.dto;

import ru.geekbrains.persist.LineItem;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    private BigDecimal price;

    private List<LineItem> lineItems;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, String description, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<LineItem> getLineItems() { return lineItems; }

    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
}
