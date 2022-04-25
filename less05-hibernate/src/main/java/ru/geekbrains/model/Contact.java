package ru.geekbrains.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @Column(nullable = false)
    private String contact;

    @ManyToOne
    private User user;

    public Contact() {
    }

    public Contact(ContactType contactType, String contact, User user) {
        this.contactType = contactType;
        this.contact = contact;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum ContactType {
        MOBILE_PHONE, HOME_PHONE, WORK_PHONE, HOME_ADDRESS, WORK_ADDRESS
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", contactType=" + contactType +
                ", contact='" + contact + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
