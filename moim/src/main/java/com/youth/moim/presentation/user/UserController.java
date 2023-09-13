package com.youth.moim.presentation.user;

import com.youth.moim.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public void signIn(UserRequest.SignInOrganizer request) {
        userService.signIn(request);
    }
}
