package com.youth.moim.presentation.user;


import com.youth.moim.ApiTest;
import com.youth.moim.api.Scenario;
import com.youth.moim.infrastructure.user.UserRepository;
import com.youth.moim.presentation.auth.AuthResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("유저 관련 API 테스트")
public class UserApiTest extends ApiTest {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserController userController;

  @Test
  @DisplayName("유저 정보 조회 테스트")
  void test20230917230236() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    AuthResponse.SignUp token = Scenario.signInApi().email(email).request()
            .signUpApi().request();

    // when
    ExtractableResponse<Response> result = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + token.token())
            .when()
            .get("/api/users/" + userIdx)
            .then().log().all()
            .extract();

    // then
    UserResponse.RetrieveUser response = result.body().as(UserResponse.RetrieveUser.class);
    assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.user().email()).isEqualTo(email);
  }
}