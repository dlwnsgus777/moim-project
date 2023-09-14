package com.youth.moim.presentation.user.api;

import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.presentation.user.UserRequest;
import io.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

public class SignInOrganizerApi {
    private String name = "이름";
    private String birth = "19930927";
    private Gender gender = Gender.MALE;
    private String id = "dlwnsgus";
    private String password = "!2Password";
    private String email = "dlwnsgus777@test.com";
    private String company = "company";
    private List<String> ignoreFoods = List.of(
            "새우"
    );
    private String description = "description";
    private MoimRule rule = MoimRule.ORGANIZER;

    public SignInOrganizerApi name(String name) {
        this.name = name;
        return this;
    }

    public SignInOrganizerApi birth(String birth) {
        this.birth = birth;
        return this;
    }

    public SignInOrganizerApi gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public SignInOrganizerApi id(String id) {
        this.id = id;
        return this;
    }

    public SignInOrganizerApi password(String password) {
        this.password = password;
        return this;
    }

    public SignInOrganizerApi email(String email) {
        this.email = email;
        return this;
    }

    public SignInOrganizerApi company(String company) {
        this.company = company;
        return this;
    }

    public SignInOrganizerApi rule(MoimRule rule) {
        this.rule = rule;
        return this;
    }

    public SignInOrganizerApi ignoreFoods(List<String> ignoreFoods) {
        this.ignoreFoods = ignoreFoods;
        return this;
    }

    public SignInOrganizerApi description(String description) {
        this.description = description;
        return this;
    }

    public Scenario request() {
        UserRequest.SignIn request = new UserRequest.SignIn(
                name,
                birth,
                gender,
                id,
                password,
                email,
                company,
                ignoreFoods,
                description,
                rule
        );

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/users/sign-in")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new Scenario();

    }
}
