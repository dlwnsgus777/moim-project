package com.youth.moim.presentation.auth.api;

import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.presentation.auth.AuthRequest;
import com.youth.moim.presentation.auth.AuthResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

public class SignUpApi {
    private String loginId = "dlwnsgus777@test.com";
    private String password = "!2Password";

    public SignUpApi loginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public SignUpApi password(String password) {
        this.password = password;
        return this;
    }

    public AuthResponse.SignUp request() {
        AuthRequest.SignUp request = new AuthRequest.SignUp(
                loginId,
                password
        );

        ExtractableResponse<Response> result = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/auth/sign-up")
                .then().log().all()
                .extract();

        return result.body().as(AuthResponse.SignUp.class);

    }
}
