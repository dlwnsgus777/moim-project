package com.youth.moim.presentation.user;

import com.youth.moim.application.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public void signInOrganizer(@RequestBody UserRequest.SignIn request) {
        userService.signIn(request);
    }
}
