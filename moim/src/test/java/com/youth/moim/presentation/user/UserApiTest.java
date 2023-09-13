package com.youth.moim.presentation.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserApiTest {
  private UserController userController;
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository = new UserRepository();
    UserStore userStore = new UserStore(userRepository);
    UserReader userReader = new UserReader(userRepository);
    UserService userService = new UserService(userStore, userReader);
    userController = new UserController(userService);
  }

  @Test
  @DisplayName("유저 저장 테스트")
  void test20230912213527() {
    // given
    UserRequest.SignInOrganizer request = new UserRequest.SignInOrganizer(
            "이름",
            "19930927",
            Gender.MALE,
            "dlwnsgus",
            "password",
            "dlwnsgus777@test.com",
            "company"
    );

    // when
    userController.signIn(request);

    // then
    User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("저장실패"));
    assertThat(userRepository.findAll().size()).isNotEqualTo(0);
    assertThat(user.getEmail()).isEqualTo(request.email());

  }

}
