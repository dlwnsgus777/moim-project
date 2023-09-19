package com.youth.moim.application.user;

import com.youth.moim.domain.user.User;
import com.youth.moim.domain.user.UserInfo;
import com.youth.moim.infrastructure.user.UserReaderImpl;
import com.youth.moim.infrastructure.user.UserStoreImpl;
import com.youth.moim.presentation.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserStoreImpl userStore;
    private final UserReaderImpl userReader;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signIn(UserRequest.SignIn request) {
        Optional<User> user = userReader.findByEmail(request.email());
        if (user.isEmpty()) {
            userStore.registerUser(request.toEntity(passwordEncoder));
        }
    }

    @Transactional(readOnly = true)
    public UserInfo.Main retrieveUser(Long idx) {
        User user = userReader.getByIdx(idx);
        return UserInfo.Main.of(user);
    }
}
