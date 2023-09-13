package com.youth.moim.presentation.user;

import com.youth.moim.domain.Gender;
import com.youth.moim.domain.User;

public class UserRequest {
    public record SignInOrganizer(
            String name,
            String birth,
            Gender gender,
            String id,
            String password,
            String email,
            String company
    ) {

        public User toEntity() {
            return new User(
                    null,
                    name,
                    birth,
                    gender,
                    id,
                    password,
                    email,
                    company
            );
        }
    }
}
