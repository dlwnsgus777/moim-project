package com.youth.moim.application.auth;

import com.youth.moim.domain.user.User;
import com.youth.moim.domain.user.UserReader;
import com.youth.moim.domain.user.UserStore;
import com.youth.moim.presentation.auth.AuthRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserStore userStore;
    private final UserReader userReader;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signIn(AuthRequest.SignIn request) {
        Optional<User> user = userReader.findByEmail(request.email());
        if (user.isEmpty()) {
            userStore.registerUser(request.toEntity(passwordEncoder));
        }
    }

    public String signUp(AuthRequest.SignUp request) {
        User findUser = userReader.getByLoginId(request.id());
        if (passwordEncoder.matches(request.password(), findUser.getPassword())) {
            return jwtTokenProvider.createToken(findUser);
        }

        throw new IllegalArgumentException("로그인에 실패했습니다.");
    }
}
