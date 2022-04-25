package ru.geekbrains.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id         // в ОРМ обязательно должен быть первичный ключ (в нашем случае это id пользователя)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cardNumber;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<LineItem> lineItems;

    public Customer() {
    }

    public Customer(Long id, String cardNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<LineItem> getLineItems() { return lineItems; }

    public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
}
