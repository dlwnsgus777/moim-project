package com.youth.moim.presentation.user;

public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void signIn(UserRequest.SignInOrganizer request) {
        userService.signIn(request);
    }
}
