package com.youth.moim.domain.user;


import com.youth.moim.presentation.user.UserRequest;
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

    public void modifyInfo(final UserRequest.ModifyUser request) {
        if (request.name() != null) this.name = request.name();
        if (request.birth() != null) this.birth = request.birth();
        if (request.gender() != null) this.gender = request.gender();
        if (request.id() != null) this.id = request.id();
        if (request.password() != null) this.password = request.password();
        if (request.email() != null) this.email = request.email();
        if (request.getIgnoreFoods() != null) this.ignoreFoods = request.getIgnoreFoods();
        if (request.description() != null) this.description = request.description();
    }

    public void changeRole(final UserRequest.ChangeRole request) {
        validChangeRole(request);
        if (this.role.equals(MoimRole.ORGANIZER)) {
            this.role = MoimRole.HOST;
            this.description = request.description();
            this.ignoreFoods = request.getIgnoreFoods();
        } else {
            this.role = MoimRole.ORGANIZER;
            this.company = request.company();
        }
    }

    private void validChangeRole(final UserRequest.ChangeRole request) {
        if (request.role().equals(MoimRole.ORGANIZER) && request.company() == null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        if (request.role().equals(MoimRole.HOST) && request.description() == null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
