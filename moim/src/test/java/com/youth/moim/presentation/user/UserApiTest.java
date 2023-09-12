package com.youth.moim.presentation.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserApiTest {

  @Test
  @DisplayName("유저 저장 테스트")
  void test20230912213527() {
    // given
    UserRequest.SignInOrganizer request = new UserRequest.SignInOrganizer(
            "이름",
            "19930927",
            "MALE",
            "dlwnsgus",
            "password",
            "dlwnsgus777@test.com",
            "company"
    );


    // when

    // then 
  }

  public static class UserRequest {
    public record SignInOrganizer(
            String name,
            String birth,
            String gender,
            String id,
            String password,
            String email,
            String company
    ) {

    }

  }
}
