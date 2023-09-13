package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;

public class UserRequest {
    public record SignInOrganizer(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            String company,
            MoimRule rule) {

        public User toEntity() {
            return User.builder()
                    .birth(birth)
                    .name(name)
                    .company(company)
                    .rule(rule)
                    .gender(gender)
                    .email(email)
                    .password(password)
                    .id(id)
                    .build();
        }
    }
}
