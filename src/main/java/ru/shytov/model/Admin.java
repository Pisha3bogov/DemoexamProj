package ru.shytov.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@ToString
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "login",nullable = false, unique = true)
    private String login;

    @NonNull
    @Column(name = "password",nullable = false)
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(@NonNull String login, @NonNull String password) {
        if(login.equals("")){
            throw new NullPointerException("Login null");
        } else if(password.equals("")){
            throw new NullPointerException("Password null");
        }
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return login.equals(admin.login) && password.equals(admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
