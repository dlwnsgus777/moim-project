package com.youth.moim.domain.user;

import lombok.Builder;

public class UserInfo {
  public record Main(
          Long idx,
          String name,
          Gender gender,
          String id,
          String email,
          String company,
          String ignoreFoods,
          String description
  ) {
    @Builder
    public Main {
    }
  }
}
