package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserRequest {
    public record SignIn(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            String company,
            List<String> ignoreFoods,
            String description,
            MoimRule rule) {

        public User toEntity() {
            if (rule.equals(MoimRule.HOST)) {
                return User.builder()
                        .birth(birth)
                        .name(name)
                        .rule(rule)
                        .gender(gender)
                        .email(email)
                        .password(password)
                        .id(id)
                        .ignoreFoods(ignoreFoods.stream().collect(Collectors.joining(",")))
                        .description(description)
                        .build();
            } else {
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

    public record SignInHost(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            List<String> ignoreFoods,
            String description,
            MoimRule rule
    ) {

    }
}
