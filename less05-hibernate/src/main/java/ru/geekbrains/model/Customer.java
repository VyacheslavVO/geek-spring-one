package ru.geekbrains.model;

import javax.persistence.*;

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
}
