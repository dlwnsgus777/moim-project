package com.youth.moim.application.user;

import com.youth.moim.domain.user.User;
import com.youth.moim.infrastructure.user.UserReaderImpl;
import com.youth.moim.infrastructure.user.UserStoreImpl;
import com.youth.moim.presentation.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStoreImpl userStoreImpl;
    private final UserReaderImpl userReaderImpl;
    private final PasswordEncoder passwordEncoder;

    public void signIn(UserRequest.SignIn request) {
        Optional<User> user = userReaderImpl.findByEmail(request.email());
        if (user.isEmpty()) {
            userStoreImpl.registerUser(request.toEntity(passwordEncoder));
        }
    }
}
