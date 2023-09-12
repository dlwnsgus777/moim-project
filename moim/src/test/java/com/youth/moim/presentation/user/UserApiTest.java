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
            Gender.MALE,
            "dlwnsgus",
            "password",
            "dlwnsgus777@test.com",
            "company"
    );
    System.out.println(request.gender());

    // when

    // then 
  }

  public static class UserRequest {
    public record SignInOrganizer(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            String company
    ) {

    }
  }

  public enum Gender {
    FEMALE, MALE
  }
}
