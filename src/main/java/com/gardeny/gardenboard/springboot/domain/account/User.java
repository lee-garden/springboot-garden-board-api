package com.gardeny.gardenboard.springboot.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(length = 30, nullable = false)
    private String email;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String phone;

    @Builder
    public User(Role role, String email, String password, String name, String phone) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
