package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

public class UserRequest {
    public record SignIn(
            String name,
            String birth,
            Gender gender,
            String id,
            @NotBlank(message = "비밀번호 값은 필수 입니다.")
            @Pattern(
                    regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
                    message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다."
            )
            String password,
            String email,
            String company,
            List<String> ignoreFoods,
            String description,
            MoimRule rule) {

        public User toEntity(PasswordEncoder passwordEncoder) {
            if (rule.equals(MoimRule.HOST)) {
                return User.builder()
                        .birth(birth)
                        .name(name)
                        .rule(rule)
                        .gender(gender)
                        .email(email)
                        .password(passwordEncoder.encode(password))
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
                        .password(passwordEncoder.encode(password))
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
