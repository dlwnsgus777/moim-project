package com.youth.moim.presentation.user;


import com.youth.moim.ApiTest;
import com.youth.moim.api.Scenario;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;
import com.youth.moim.infrastructure.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    Scenario.signInOrganizerApi().email(email).request();

    // then
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getRule()).isEqualTo(MoimRule.ORGANIZER);
  }

  @Test
  @DisplayName("모임 참여자 유저 저장 테스트")
  void test20230914183028() {
    //given
    String name = "이름";
    String birth = "19930927";
    Gender gender = Gender.MALE;
    String id = "dlwnsgus";
    String password = "password";
    String email = "dlwnsgus777@test.com";
    List<String> ignoreFoods = List.of(
            "새우"
    );
    String description = "description";
    MoimRule rule = MoimRule.HOST;

    UserRequest.SignInHost request = new UserRequest.SignInHost(
            name,
            birth,
            gender,
            id,
            password,
            email,
            ignoreFoods,
            description,
            rule
    );

    //when
    userController.signInHost(request);


    // then
    User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(email);
    assertThat(user.getRule()).isEqualTo(MoimRule.HOST);
  }
}