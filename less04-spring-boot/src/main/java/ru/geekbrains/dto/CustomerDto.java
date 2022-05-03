package ru.geekbrains.dto;

import ru.geekbrains.persist.LineItem;
import ru.geekbrains.persist.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CustomerDto {

    private Long id;

    @NotBlank
    private String cardNumber;

    private User user;

    private List<LineItem> lineItems;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String cardNumber, User user, List<LineItem> lineItems) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.user = user;
        this.lineItems = lineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
