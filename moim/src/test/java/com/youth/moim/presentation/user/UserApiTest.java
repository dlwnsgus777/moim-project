package com.youth.moim.presentation.user;

import com.youth.moim.ApiTest;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;
import com.youth.moim.infrastructure.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest extends ApiTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("모임 주최자 유저 저장 테스트")
  void test20230912213527() {
    // given
    UserRequest.SignInOrganizer request = new UserRequest.SignInOrganizer(
            "이름",
            "19930927",
            Gender.MALE,
            "dlwnsgus",
            "password",
            "dlwnsgus777@test.com",
            "company",
            MoimRule.ORGANIZER
    );

    // when
    RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/users/sign-in")
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value());

    // then
    User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(request.email());
    assertThat(user.getRule()).isEqualTo(MoimRule.ORGANIZER);
  }
}
