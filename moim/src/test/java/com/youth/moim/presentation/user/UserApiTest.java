package com.youth.moim.presentation.user;


import com.youth.moim.ApiTest;
import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;
import com.youth.moim.infrastructure.user.UserRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("유저 관련 API 테스트")
public class UserApiTest extends ApiTest {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserController userController;

  @Test
  @DisplayName("모임 주최자 유저 저장 테스트")
  void test20230912213527() {
    // given
    String email = "dlwnsgus777@test.com";
    String password = "!2Password";
    Scenario.signInApi().email(email).password(password).ignoreFoods(null).description(null).request();

    // then
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getIgnoreFoods()).isNull();
    assertThat(user.getDescription()).isNull();
    assertThat(user.getPassword()).isNotEqualTo(password);
    assertThat(user.getRule()).isEqualTo(MoimRule.ORGANIZER);
  }

  @Test
  @DisplayName("모임 참여자 유저 저장 테스트")
  void test20230914183028() {
    //given
    MoimRule rule = MoimRule.HOST;
    String email = "dlwnsgus777@test.com";
    String password = "!2Password";
    Scenario.signInApi().email(email).password(password).rule(rule).request();


    // then
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getIgnoreFoods()).isNotNull();
    assertThat(user.getDescription()).isNotNull();
    assertThat(user.getPassword()).isNotEqualTo(password);
    assertThat(user.getRule()).isEqualTo(MoimRule.HOST);
  }

  @Test
  @DisplayName("비밀번호 정책에 맞지 않으면 실패한다.")
  void test202309141830289() {
    //given
    String name = "이름";
    LocalDate birth = LocalDate.of(1993, 9, 27);
    Gender gender = Gender.MALE;
    String id = "dlwnsgus";
    String password = "password";
    String email = "dlwnsgus777@test.com";
    String company = "company";
    List<String> ignoreFoods = List.of(
            "새우"
    );
    String description = "description";
    MoimRule rule = MoimRule.ORGANIZER;
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


    // then
    RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when()
            .post("/api/auth/sign-in")
            .then().log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());

  }
}