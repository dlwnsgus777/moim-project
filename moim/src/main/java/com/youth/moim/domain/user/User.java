package com.youth.moim.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MoimRole role;

    @Column(name = "company")
    private String company;

    @Column(name = "ignore_foods")
    private String ignoreFoods;

    @Column(name = "description")
    private String description;

    @Builder
    public User(
        Long idx,
        String name,
        LocalDate birth,
        Gender gender,
        String id,
        String password,
        String email,
        MoimRole role,
        String company,
        String ignoreFoods,
        String description
    ) {
        this.idx = idx;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
        this.company = company;
        this.ignoreFoods = ignoreFoods;
        this.description = description;
    }
}
