package com.youth.moim.presentation.auth.api;

import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRole;
import com.youth.moim.presentation.auth.AuthRequest;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

public class SignInApi {
    private String name = "이름";
    private LocalDate birth = LocalDate.of(1993, 9, 27);
    private Gender gender = Gender.MALE;
    private String id = "dlwnsgus";
    private String password = "!2Password";
    private String email = "dlwnsgus777@test.com";
    private String company = "company";
    private List<String> ignoreFoods = List.of(
        "새우"
    );
    private String description = "description";
    private MoimRole role = MoimRole.ORGANIZER;

    public SignInApi name(String name) {
        this.name = name;
        return this;
    }

    public SignInApi birth(LocalDate birth) {
        this.birth = birth;
        return this;
    }

    public SignInApi gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public SignInApi id(String id) {
        this.id = id;
        return this;
    }

    public SignInApi password(String password) {
        this.password = password;
        return this;
    }

    public SignInApi email(String email) {
        this.email = email;
        return this;
    }

    public SignInApi company(String company) {
        this.company = company;
        return this;
    }

    public SignInApi role(MoimRole role) {
        this.role = role;
        return this;
    }

    public SignInApi ignoreFoods(List<String> ignoreFoods) {
        this.ignoreFoods = ignoreFoods;
        return this;
    }

    public SignInApi description(String description) {
        this.description = description;
        return this;
    }

    public Scenario request() {
        AuthRequest.SignIn request = new AuthRequest.SignIn(
            name,
            birth,
            gender,
            id,
            password,
            email,
            company,
            ignoreFoods,
            description,
            role
        );

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/auth/sign-in")
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value());

        return new Scenario();

    }
}
