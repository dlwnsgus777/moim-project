package com.youth.moim.application;

import com.youth.moim.domain.User;
import com.youth.moim.infrastructure.UserReaderImpl;
import com.youth.moim.infrastructure.UserStoreImpl;
import com.youth.moim.presentation.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStoreImpl userStoreImpl;
    private final UserReaderImpl userReaderImpl;

    public void signIn(UserRequest.SignInOrganizer request) {
        Optional<User> user = userReaderImpl.findByEmail(request.email());
        if (user.isEmpty()) {
            userStoreImpl.registerUser(request.toEntity());
        }
    }
}
