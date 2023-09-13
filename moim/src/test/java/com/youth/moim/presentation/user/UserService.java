package com.youth.moim.presentation.user;

import java.util.Optional;

public class UserService {
    UserStore userStore;
    UserReader userReader;

    public UserService(UserStore userStore, UserReader userReader) {
        this.userStore = userStore;
        this.userReader = userReader;
    }

    public void signIn(UserRequest.SignInOrganizer request) {
        Optional<User> user = userReader.findByEmail(request.email());
        if (user.isEmpty()) {
            userStore.registerUser(request.toEntity());
        }
    }
}
