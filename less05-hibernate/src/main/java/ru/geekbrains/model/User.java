package ru.geekbrains.model;

import javax.persistence.*;

@Entity         // обязательная аннотация говорит о том, что эта сущность является таблицей
@Table(name = "users")
public class User{

    @Id         // в ОРМ обязательно должен быть первичный ключ (в нашем случае это id пользователя)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String email;

    @Column(nullable = false, length = 1024)
    private String password;
    // обязательно должен быть конструктор по умолчанию (без параметров)
    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // обязательно для каждого поля Column должен существовать Getter и Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
