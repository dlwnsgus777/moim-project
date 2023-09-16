package com.youth.moim.presentation.user;

import com.youth.moim.application.user.UserService;
import com.youth.moim.domain.user.Gender;
import com.youth.moim.domain.user.MoimRule;
import com.youth.moim.infrastructure.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public void signInOrganizer(@RequestBody @Valid UserRequest.SignIn request) {
        userService.signIn(request);
    }

}
