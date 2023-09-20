package com.youth.moim.presentation.user;


import com.youth.moim.ApiTest;
import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.MoimRole;
import com.youth.moim.domain.user.User;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("유저 관련 API 테스트")
public class UserApiTest extends ApiTest {
  @Autowired
  private UserRepository userRepository;


  @Test
  @DisplayName("주최자가 자신의 정보를 조회할 수 있다.")
  void test20230917230236() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    MoimRole role = MoimRole.ORGANIZER;
    AuthResponse.SignUp token = Scenario.signInApi().email(email).role(role).request()
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
    assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(response.user().email()).isEqualTo(email),
            () -> assertThat(response.user().ignoreFoods()).isNull()
    );

  }

  @Test
  @DisplayName("참여자가 자신의 정보를 조회할 수 있다.")
  void test20230917530236() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    MoimRole role = MoimRole.HOST;
    AuthResponse.SignUp token = Scenario.signInApi().email(email).role(role).request()
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
    assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(response.user().email()).isEqualTo(email),
            () -> assertThat(response.user().company()).isNull()
    );
  }

  @Test
  @DisplayName("참여자가 자신의 역할을 변경할 수 있다.")
  void test20230817530236() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    MoimRole role = MoimRole.HOST;
    AuthResponse.SignUp token = Scenario.signInApi().email(email).role(role).request()
            .signUpApi().request();

    // when
    ExtractableResponse<Response> getUserApiCall = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + token.token())
            .when()
            .get("/api/users/" + userIdx)
            .then().log().all()
            .extract();

    User beforeUser = userRepository.findByIdx(userIdx)
            .orElseThrow(() -> new IllegalArgumentException("테스트 실패"));


    RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + token.token())
            .when()
            .post("/api/users/" + userIdx + "/change-role")
            .then().log().all()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .extract();

    User afterUser = userRepository.findByIdx(userIdx)
            .orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

    // then
    assertAll(
            () -> assertThat(beforeUser.getRole()).isEqualTo(MoimRole.HOST),
            () -> assertThat(afterUser.getRole()).isEqualTo(MoimRole.ORGANIZER)
    );
  }

  @Test
  @DisplayName("참여자가 자신의 정보를 수정할 수 있다.")
  void test20230917530216() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    String name = "name";
    String changeName = "changeName";
    MoimRole role = MoimRole.HOST;
    AuthResponse.SignUp token = Scenario.signInApi().email(email).role(role).name(name).request()
            .signUpApi().request();

    UserRequest.ModifyUser request = UserRequest.ModifyUser.builder()
            .name(changeName)
            .build();
    final User beforeUser = userRepository.findByIdx(userIdx)
            .orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

    // when
    ExtractableResponse<Response> result = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + token.token())
            .body(request)
            .when()
            .patch("/api/users/" + userIdx)
            .then().log().all()
            .extract();

    // then
    final User user = userRepository.findByIdx(userIdx)
            .orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

    assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value()),
            () -> assertThat(user.getName()).isEqualTo(changeName),
            () -> assertThat(beforeUser.getName()).isEqualTo(name)
    );
  }

  @Test
  @DisplayName("참여자가 자신의 비밀번호를 수정할때 비밀번호 규칙을 체크한다.")
  void test20230917530206() {
    // given
    Long userIdx = 1L;
    String email = "dlwnsgus777@test.com";
    String name = "name";
    String changeName = "changeName";
    String password = "123";
    MoimRole role = MoimRole.HOST;
    AuthResponse.SignUp token = Scenario.signInApi().email(email).role(role).name(name).request()
            .signUpApi().request();

    UserRequest.ModifyUser request = UserRequest.ModifyUser.builder()
            .name(changeName)
            .password(password)
            .build();
    final User beforeUser = userRepository.findByIdx(userIdx)
            .orElseThrow(() -> new IllegalArgumentException("테스트 실패"));

    // when
    ExtractableResponse<Response> result = RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header("Authorization", "Bearer " + token.token())
            .body(request)
            .when()
            .patch("/api/users/" + userIdx)
            .then().log().all()
            .extract();

    // then
    assertThat(result.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }
}