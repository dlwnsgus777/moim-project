package com.youth.moim.presentation.auth;

import com.youth.moim.application.auth.AuthService;
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
  public void signIn(@RequestBody @Valid AuthRequest.SignIn request) {
    authService.signIn(request);
  }

  @PostMapping("/sign-up")
  public AuthResponse.SignUp signUp(@RequestBody @Valid AuthRequest.SignIn request) {
    String token = authService.signUp(request);
    return new AuthResponse.SignUp(token);
  }
}
