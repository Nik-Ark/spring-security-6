package com.nikki.security5.custom_auth.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    Set<User> users;

    @Override
    public String toString() {
        return "Authority: {" +
                "name: '" + name + '\'' +
                '}';
    }
}
