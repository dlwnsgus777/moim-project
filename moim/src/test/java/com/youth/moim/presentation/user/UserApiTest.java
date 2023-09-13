package com.youth.moim.presentation.user;

import com.youth.moim.domain.Gender;
import com.youth.moim.domain.MoimRule;
import com.youth.moim.domain.User;
import com.youth.moim.infrastructure.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserApiTest {
  @Autowired
  private UserController userController;
  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
  }

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
    userController.signIn(request);

    // then
    User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(request.email());
    assertThat(user.getRule()).isEqualTo(MoimRule.ORGANIZER);
  }
}
