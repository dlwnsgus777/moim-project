package com.youth.moim.infrastructure.user;

import com.youth.moim.domain.user.User;
import com.youth.moim.domain.user.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;
    public void registerUser(User entity) {
        userRepository.save(entity);
    }
}
