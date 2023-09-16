package com.youth.moim.presentation.auth;

import com.youth.moim.application.auth.AuthService;
import com.youth.moim.presentation.user.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/sign-in")
  @ResponseStatus(HttpStatus.CREATED)
  public void signInOrganizer(@RequestBody @Valid AuthRequest.SignIn request) {
    authService.signIn(request);
  }
}
