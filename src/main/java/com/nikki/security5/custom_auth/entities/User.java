package com.nikki.security5.custom_auth.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "access_key")
    private String accessKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;

    @Override
    public String toString() {
        return "User: {" +
                "id: " + id +
                ", username: '" + username + '\'' +
                ", authorities: '" + getAuthorities() + '\'' +
                '}';
    }
}
