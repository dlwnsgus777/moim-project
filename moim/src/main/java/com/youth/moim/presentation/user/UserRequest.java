package com.youth.moim.presentation.user;

import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRole;
import com.youth.moim.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserRequest {
    public record ModifyUser(
        Long idx,
        String name,
        LocalDate birth,
        Gender gender,
        String id,
        @Pattern(
            regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다."
        )
        String password,

        @Email
        String email,
        String company,
        List<String> ignoreFoods,
        String description,
        MoimRole role
    ) {
        @Builder
        public ModifyUser {
        }

        public String getIgnoreFoods() {
            if (ignoreFoods == null || ignoreFoods.isEmpty()) {
                return null;
            }

            return this.ignoreFoods.stream().collect(Collectors.joining(","));
        }
    }
    public record SignIn(
        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @NotNull(message = "생년월일을 입력해주세요.")
        LocalDate birth,

        @NotNull(message = "성별을 선택해주세요.")
        Gender gender,

        @NotBlank(message = "로그인 아이디를 입력해주세요.")
        String id,

        @NotBlank(message = "비밀번호 값은 필수 입니다.")
        @Pattern(
            regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다."
        )
        String password,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email
        String email,
        String company,
        List<String> ignoreFoods,
        String description,
        MoimRole role) {

        public User toEntity(PasswordEncoder passwordEncoder) {
            if (role.equals(MoimRole.HOST)) {
                return User.builder()
                    .birth(birth)
                    .name(name)
                    .role(role)
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
                    .role(role)
                    .gender(gender)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .id(id)
                    .build();
            }

        }
    }

    public record ChangeRole(
        List<String> ignoreFoods,
        String company,
        String description,
        @NotNull(message = "변경할 역할을 선택해주세요.")
        MoimRole role
    ) {
        @Builder
        public ChangeRole {
        }

        public String getIgnoreFoods() {
            if (ignoreFoods == null || ignoreFoods.isEmpty()) {
                return null;
            }

            return this.ignoreFoods.stream().collect(Collectors.joining(","));
        }
    }
}
